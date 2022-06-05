using UnityEngine;

public class CrouchingState : CharacterState
{
    private float chargeTime;

    public CrouchingState() { Debug.Log("CHANGED STATE TO CrouchingState."); }

    public override CharacterStateType GetStateType() { return CharacterStateType.CROUCHING; }

    public override CharacterState HandleInput(PlatformerCharacter2D character, CharacterInput input)
    {
        // Can't stand up or jump anyway
        if (character.IsTooCloseToCeiling()) { return null; }

        // Special charge jump released
        else if (chargeTime > 0f && (input.jumpReleased || !input.crouch)) { return new JumpingState(chargeTime); }

        // Still crouching
        else if (input.crouch) { return null; }

        else if (!character.IsGrounded()) { return new FreeFallingState(); }

        // Stopped crouching
        else { return new StandingState(); }
    }

    public override void MoveCharacter(PlatformerCharacter2D character, CharacterInput input)
    {
        if (!input.jumpReleased) {
            // Accumulate charge time and do not move
            chargeTime += Time.deltaTime;
        } else {
            // Reduce the speed by the crouchSpeed multiplier
            float slowedMove = input.move * character.GetCrouchSpeed();

            character.UpdateGroundVelocity(slowedMove);
            base.MoveCharacter(character, new CharacterInput(slowedMove, input.crouch, input.jump));
        }
    }
}