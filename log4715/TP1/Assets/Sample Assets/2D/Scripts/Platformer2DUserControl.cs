using UnityEngine;

[RequireComponent(typeof(PlatformerCharacter2D))]
public class Platformer2DUserControl : MonoBehaviour 
{
	private PlatformerCharacter2D character;
    private bool jump;
    private bool jumpReleased;


	void Awake()
	{
		character = GetComponent<PlatformerCharacter2D>();
	}

    void Update ()
    {
        // Read the jump input in Update so button presses aren't missed.
#if CROSS_PLATFORM_INPUT
        if (CrossPlatformInput.GetButtonDown("Jump")) jump = true;
#else
		if (Input.GetButtonDown("Jump")) jump = true;
#endif

    }

	void FixedUpdate()
	{
        jumpReleased = true;

		// Read the inputs.
		bool crouch = Input.GetKey(KeyCode.LeftControl);

#if CROSS_PLATFORM_INPUT
        if (CrossPlatformInput.GetButton("Jump")) jumpReleased = false;
#else
        if (Input.GetButton("Jump")) jumpReleased = false;
#endif


        float h = Input.GetAxisRaw("Horizontal");

        // Pass all parameters to the character control script.
        CharacterInput input = new CharacterInput(h, crouch, jump, jumpReleased);
		character.Move(input);

        // Reset the jump input once it has been used.
	    jump = false;
	}
}
