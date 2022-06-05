public struct CharacterInput
{
    public CharacterInput(float _move = 0f, bool _crouch = false, bool _jump = false, bool _jumpReleased = true)
    {
        move = _move;
        crouch = _crouch;
        jump = _jump;
        jumpReleased = _jumpReleased;
    }

    public override string ToString()
    {
        return "Move: " + move + "; Crouch: " + crouch + "; Jump: " + jump + "; JumpReleased: " + jumpReleased + ";";
    }

    public float move;
    public bool crouch;
    public bool jump;
    public bool jumpReleased;
}