using UnityEngine;

public class FreeFallingState : CharacterState
{
    public FreeFallingState() { Debug.Log("CHANGED STATE TO FreeFallingState."); }

    public override CharacterStateType GetStateType() { return CharacterStateType.FREE_FALLING; }

    public override CharacterState HandleInput(PlatformerCharacter2D character, CharacterInput input)
    {
        if (input.jump) { return new JumpingState(); }
        else if (character.IsOnWall()) { return new WallState(); }
        else if (character.IsGrounded()) { return new StandingState(); }
        else { return null; }
    }

    public override void MoveCharacter(PlatformerCharacter2D character, CharacterInput input)
    {
        character.UpdateAirVelocity(input.move);
        base.MoveCharacter(character, input);
    }
}