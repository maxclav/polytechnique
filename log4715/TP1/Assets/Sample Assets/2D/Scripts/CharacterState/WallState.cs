using UnityEngine;

public class WallState : CharacterState
{
    public WallState()
    {
        Debug.Log("CHANGED STATE TO WallState.");
    }
    
    public override CharacterStateType GetStateType() { return CharacterStateType.WALL; }

    public override CharacterState HandleInput(PlatformerCharacter2D character, CharacterInput input)
    {
        if (character.IsGrounded()) { return new StandingState(); }
        else if (input.jump) { return new JumpingState(); }
        else if (!character.IsOnWall()) { return new FreeFallingState(); }
        else { return null; }
    }

    public override void MoveCharacter(PlatformerCharacter2D character, CharacterInput input)
    {
        character.UpdateAirVelocity(input.move);
        base.MoveCharacter(character, input);
    }
}