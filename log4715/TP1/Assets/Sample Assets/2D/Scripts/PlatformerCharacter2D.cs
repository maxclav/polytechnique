using UnityEngine;

public class PlatformerCharacter2D : MonoBehaviour 
{
	bool facingRight = true;						        // For determining which way the player is currently facing.

	[SerializeField] float maxSpeed = 10f;		            // The fastest the player can travel in the x axis.
	[SerializeField] float jumpForce = 400f;			    // Amount of force added when the player jumps.	
    [SerializeField] float airJumpForce = 400f;             // Amount of force added when the player air-jumps.
    [SerializeField] float wallJumpForce = 400f;            // Amount of force added in both directions when the player jumps from a wall.

    [SerializeField] float maxChargedJumpForce = 800f;      // Maximum amount of force added when the player does a charged jump.
    [SerializeField] float maxJumpChargeTime = 5;           // Maximum time the player can accumulate force on a charged jump, in seconds.

    [Range(0, 1)]
	[SerializeField] float crouchSpeed = .36f;	            // Amount of maxSpeed applied to crouching movement. 1 = 100%
	
    [SerializeField] int numberOfAirJumps = 1;                  // Quantity of allowrd jumps while in the air
	[SerializeField] bool airControl = false;	            // Whether or not a player can steer while jumping;
    [SerializeField] float airControlStrength = 25f;
    [SerializeField] LayerMask whatIsGround;		        // A mask determining what is ground to the character
    [SerializeField] LayerMask whatIsWall;                  // A mask determining what is a wall to the character
    [SerializeField] float wallSlideDrag = 15f;

    Transform groundCheck;								    // A position marking where to check if the player is grounded.
	float groundedRadius = .2f;							    // Radius of the overlap circle to determine if grounded
	bool grounded = false;						            // Whether or not the player is grounded.
    Transform ceilingCheck;						            // A position marking where to check for ceilings
	float ceilingRadius = .01f;					            // Radius of the overlap circle to determine if the player can stand up

    //Transform faceWallCheck;                                // A position marking where to check if the player touches a wall on its face.
    RaycastHit2D wallCheck;
    bool isOnWall = false;                                    // Wheter or not the player touches the wall.
    float wallHitDistance = 0.4f;                             // distance of the raycast to determine if touching a wall

    Animator anim;                                          // Reference to the player's animator component
    CharacterState state;                                   // Describe the current global state of the player, like standing, jumping, crouching ...

    // Accessors
    public float GetCrouchSpeed() { return crouchSpeed; }
    public int GetNumberOfAirJumps() { return numberOfAirJumps; }
    public bool HasAirControl() { return airControl; }
    public bool IsGrounded() { return grounded; }
    public bool IsOnWall() { return isOnWall; }
    public bool IsFalling() { return GetComponent<Rigidbody2D>().velocity.y < 0; }
    public bool IsTooCloseToCeiling() {
        return Physics2D.OverlapCircle(ceilingCheck.position, ceilingRadius, whatIsGround);
    }


    void Awake()
	{
		// Setting up references.
		groundCheck = transform.Find("GroundCheck");
		ceilingCheck = transform.Find("CeilingCheck");
		anim = GetComponent<Animator>();
        state = new StandingState();
	}
        

	void FixedUpdate()
	{
		// The player is grounded if a circlecast to the groundcheck position hits anything designated as ground
		grounded = Physics2D.OverlapCircle(groundCheck.position, groundedRadius, whatIsGround);
		anim.SetBool("Ground", grounded);

        Physics2D.queriesStartInColliders = false;
        wallCheck = Physics2D.Raycast(transform.position, Vector2.right * transform.localScale.x, wallHitDistance, whatIsWall);
        isOnWall = wallCheck.collider != null;
        Debug.DrawLine(transform.position, (Vector2)transform.position + Vector2.right * transform.localScale.x * wallHitDistance, Color.red);

		// Set the vertical animation
		anim.SetFloat("vSpeed", GetComponent<Rigidbody2D>().velocity.y);
	}


	public void Move(CharacterInput input)
	{
        // Give input to last state so it can select the next one.
        CharacterState nextState = state.HandleInput(this, input);

        if (nextState != null) { // null => Did not change state
            state = nextState;
            if(nextState.GetStateType() == CharacterStateType.WALL)
                GetComponent<Rigidbody2D>().drag = wallSlideDrag;
            else
                GetComponent<Rigidbody2D>().drag = 1;
        }

        state.MoveCharacter(this, input);
        UpdateAnimation(state.GetStateType(), input);
	}

    private void UpdateAnimation(CharacterStateType type, CharacterInput input)
    {
        switch (type)
        {
            case CharacterStateType.STANDING:
                anim.SetFloat("Speed", Mathf.Abs(input.move));
                anim.SetBool("Crouch", false);
                anim.SetBool("Ground", true);
                break;
            case CharacterStateType.CROUCHING:
                float move = input.move;
                // don't move if charging
                if (!input.jumpReleased) { move = 0f; }
                anim.SetFloat("Speed", Mathf.Abs(move));
                anim.SetBool("Crouch", true);
                anim.SetBool("Ground", true);
                break;
            case CharacterStateType.JUMPING:
            case CharacterStateType.FREE_FALLING:
                anim.SetFloat("Speed", Mathf.Abs(input.move));
                anim.SetBool("Crouch", false);
                anim.SetBool("Ground", false);
                break;
            case CharacterStateType.WALL:
                // TODO
                break;
            default:
                break;
        }
    }

	
	private void Flip ()
	{
		// Switch the way the player is labelled as facing.
		facingRight = !facingRight;
		
		// Multiply the player's x local scale by -1.
		Vector3 theScale = transform.localScale;
		theScale.x *= -1;
		transform.localScale = theScale;
	}

    /// <summary>
    /// Flips the character if the input makes it doing so.
    /// </summary>
    /// <param name="move">The moving value, which is greater than 0 
    /// if going right and lesser than 0 if going left</param>
    public void FlipIfNeeded(float move)
    {
        if ((move > 0 && !facingRight) || move < 0 &&  facingRight) {
            Flip();
        }
    }

    /// <summary>
    /// Changes the character's velocity based on input.
    /// </summary>
    /// <param name="move">Number representing the quantity the character has to move.</param>
    public void UpdateGroundVelocity(float move)
    {
        GetComponent<Rigidbody2D>().velocity = new Vector2(move * maxSpeed, GetComponent<Rigidbody2D>().velocity.y);
    }

    /// <summary>
    /// Adds value to the character's velocity based on input.
    /// </summary>
    /// <param name="move">Number representing the quantity the character has to move.</param>
    public void UpdateAirVelocity(float move)
    {
        GetComponent<Rigidbody2D>().AddForce(new Vector2(move * airControlStrength, 0f));
    }

    /// <summary>
    /// Makes the character jump by adding a vertical force to its body.
    /// </summary>
    /// <param name="chargeTime">Charging time accumulated before the jump.</param>
    public void Jump(float chargeTime = 0f)
    {
        float chargeBoost = Mathf.Min(Mathf.Max(chargeTime / maxJumpChargeTime, 0f), 1f);
        float finalJumpForce = (maxChargedJumpForce - jumpForce) * chargeBoost + jumpForce;
        GetComponent<Rigidbody2D>().AddForce(new Vector2(0f, finalJumpForce));
    }

    /// <summary>
    /// Makes the character jump by adding a vertical force to its body.
    /// Uses a different force value than a normal jump, since it is an air jump.
    /// The vertical forces are canceled before applying the new one, to make 
    /// air jumps feel natural.
    /// </summary>
    public void AirJump()
    {
        GetComponent<Rigidbody2D>().velocity = new Vector2(GetComponent<Rigidbody2D>().velocity.x, 0f);
        GetComponent<Rigidbody2D>().AddForce(new Vector2(0f, airJumpForce));
    }

    /// <summary>
    /// Makes the character jump from a wall by adding a vertical and a horizontal force to its body.
    /// </summary>
    public void WallJump()
    {
        GetComponent<Rigidbody2D>().velocity = new Vector2(0f, 0f);
        GetComponent<Rigidbody2D>().AddForce(new Vector2(wallCheck.normal.x * wallJumpForce, wallJumpForce));
        Flip();
    }
}
