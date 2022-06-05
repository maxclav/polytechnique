using UnityEngine;

public class StandingState : CharacterState
{
    public StandingState() { Debug.Log("CHANGED STATE TO StandingState."); }

    public override CharacterStateType GetStateType() { return CharacterStateType.STANDING; }

    public override CharacterState HandleInput(PlatformerCharacter2D character, CharacterInput input)
    {
        if (input.jump) { return new JumpingState(); }
        else if (input.crouch) { return new CrouchingState(); }
        else if (!character.IsGrounded()) { return new FreeFallingState(); }
        else { return null; }
    }

    public override void MoveCharacter(PlatformerCharacter2D character, CharacterInput input)
    {
        character.UpdateGroundVelocity(input.move);
        base.MoveCharacter(character, input);
    }
}