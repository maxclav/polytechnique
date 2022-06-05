using UnityEngine;

public class JumpingState : CharacterState
{
    private bool isStartingJump;
    private int usedAirJumps;
    private float chargeTime;

    public JumpingState()
    {
        Debug.Log("CHANGED STATE TO JumpingState.");
        usedAirJumps = 0;
        chargeTime = 0f;
        isStartingJump = true;
    }

    public JumpingState(float _chargedBoost)
    {
        Debug.Log("CHANGED STATE TO JumpingState with boost of " + chargeTime + " seconds.");
        usedAirJumps = 0;
        chargeTime = _chargedBoost;
        isStartingJump = true;
    }

    public override CharacterStateType GetStateType() { return CharacterStateType.JUMPING; }

    public override CharacterState HandleInput(PlatformerCharacter2D character, CharacterInput input)
    {
        // That way, we don't make it land before it really is jumping
        if (isStartingJump && !character.IsGrounded()) {
            isStartingJump = false;
        }

        if (!isStartingJump && character.IsGrounded()) { return new StandingState(); }
        else if (character.IsOnWall()) { return new WallState(); }
        else { return null; }
    }

    public override void MoveCharacter(PlatformerCharacter2D character, CharacterInput input)
    {
        // Only control the player if airControl is turned on
        if (character.HasAirControl()) {
            character.UpdateAirVelocity(input.move);
            base.MoveCharacter(character, input);
        }

        // Charged jump
        if (chargeTime > 0f) {
            Debug.Log("CHARGED JUMPING");
            character.Jump(chargeTime);

            // Reset the boost, because it has been used
            chargeTime = 0f;
        }

        // Normal jump
        else if (input.jump) {
            // The player can always jump from the ground
            if (character.IsGrounded()) {
                Debug.Log("JUMPING");
                character.Jump();
            }

            // The character can jump from a wall without expending air jumps
            else if (character.IsOnWall()) {
                Debug.Log("WALL JUMPING");
                character.WallJump();
            }

            // But we limit the number of air jumps
            else if (usedAirJumps < character.GetNumberOfAirJumps()) {
                Debug.Log("AIR JUMPING");
                character.AirJump();
                usedAirJumps++;
            }
        }
    }
}