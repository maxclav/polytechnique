using UnityEngine;

public enum CharacterStateType { DEFAULT, STANDING, CROUCHING, JUMPING, WALL, FREE_FALLING };

/// <summary>
/// This State pattern is heavily inspired by
/// http://gameprogrammingpatterns.com/state.
/// </summary>
public class CharacterState
{
    public virtual CharacterStateType GetStateType() { return CharacterStateType.DEFAULT; }

    /// <summary>
    /// Select the next State from available data.
    /// </summary>
    public virtual CharacterState HandleInput(PlatformerCharacter2D character, CharacterInput input) { return null; }

    /// <summary>
    /// Moves the player according to the current State's rules.
    /// </summary>
    public virtual void MoveCharacter(PlatformerCharacter2D character, CharacterInput input)
    {
        // If the input is moving the player right and the player is facing left or vice versa, flip it.
        character.FlipIfNeeded(input.move);
    }
}