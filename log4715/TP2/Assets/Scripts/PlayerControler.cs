using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class PlayerControler : MonoBehaviour
{
    private bool isGameOver { get; set; }

    // Déclaration des constantes
    private static readonly Vector3 FlipRotation = new Vector3(0, 180, 0);
    private static readonly Vector3 CameraPosition = new Vector3(10, 1, 0);
    private static readonly Vector3 InverseCameraPosition = new Vector3(-10, 1, 0);

    private bool isGameFinished;
    private bool isGameWon;

    // Déclaration des variables

    bool _Grounded { get; set; }
    bool _OnWall { get; set; }
    Vector3 _WallNormal { get; set; }
    bool _Flipped { get; set; }
    Animator _Anim { get; set; }
    Rigidbody _Rb { get; set; }

    // Valeurs exposées
    Camera _MainCamera { get; set; }
    AudioSource _source { get; set; }
    PistolController _Gun { get; set; }
    Transform _ShoulderPoint { get; set; }
    bool _AirControl = true;
    bool _FreezeControl = false;
    bool _IsGunEmpty = false;
    bool _IsHooked = false;

    public bool timePowerUp;
    public bool shieldPowerUp;

    // Valeurs exposées
    [SerializeField]
    float MoveSpeed = 5.0f;

    [SerializeField]
    float JumpForce = 10f;

    [SerializeField]
    float WallJumpForce = 10f;

    [SerializeField] 
    float WallSlideDrag = 3f;

    [SerializeField]
    LayerMask WhatIsGround;

    [SerializeField]
    LayerMask WhatIsBasePlane;

    // Awake se produit avait le Start. Il peut être bien de régler les références dans cette section.
    void Awake()
    {
        _Anim = GetComponent<Animator>();
        _Rb = GetComponent<Rigidbody>();
        _MainCamera = Camera.main;

        GameObject GunPrefab = GameObject.Find("Gun/Pistol");
        _Gun = GunPrefab.GetComponent<PistolController>();

        _ShoulderPoint = transform.Find("ShoulderPoint");
    }

    // Utile pour régler des valeurs aux objets
    void Start()
    {
        isGameOver = false;
        _Grounded = false;
        _Flipped = false;
    }

    // Vérifie les entrées de commandes du joueur
    void Update()
    {
        if(!isGameOver)
        {
            var axis = Input.GetAxisRaw("Horizontal");// * MoveSpeed;

            CheckIfGrounded();

            if (!_FreezeControl)
            {
                HorizontalMove(axis);
                //FlipCharacter(horizontal);
                CheckIsOnWall();
                CheckJump();

                if (_IsHooked)
                {
                    CheckCeilingHook();
                }

                Vector3 mousePos = MousePosToBasePlaneCoord(Input.mousePosition);

                CheckFire();
                UpdateGunPosition(mousePos);
            }
        }
        else
        {
            _Rb.velocity = new Vector3(_Rb.velocity.x, _Rb.velocity.y, 0);
            _Anim.SetFloat("MoveSpeed", 0);

            _Anim.SetBool("Grounded", true);
            _Anim.SetBool("Jump", false);
            _Anim.SetBool("Wave", true);
        }
    }

    // Gère le mouvement horizontal
    void HorizontalMove(float axis)
    {
        if (_Grounded || _IsHooked)
        {
            var horizontal = axis * MoveSpeed;
            _Rb.velocity = new Vector3(_Rb.velocity.x, _Rb.velocity.y, horizontal);
            _Anim.SetFloat("MoveSpeed", Mathf.Abs(horizontal));
        }
        else if (!_Grounded)
        {
            if (_AirControl && axis != 0)
            {
                var horizontal = axis * MoveSpeed;
                _Rb.velocity = new Vector3(_Rb.velocity.x, _Rb.velocity.y, horizontal);
                _Anim.SetFloat("MoveSpeed", Mathf.Abs(horizontal));
            }
        }
        
    }

    void CheckIsOnWall()
    {
        Vector3 fwd = transform.TransformDirection(Vector3.forward * 0.3f);
        Vector3 back = transform.TransformDirection(Vector3.back * 0.3f);
        
        Vector3 offset = new Vector3(0f,0.5f,0f);
        Vector3 pos = transform.position + offset;
        
        RaycastHit hit;

        Debug.DrawRay(pos, fwd, Color.green);
        Debug.DrawRay(pos, back, Color.red);

        if (Physics.Raycast(pos, fwd, out hit, 0.3f)) {
            _Rb.drag = WallSlideDrag;
            _OnWall = true;
            _WallNormal = hit.normal;
        } else if (Physics.Raycast(pos, back, out hit, 0.3f)) {
            _Rb.drag = WallSlideDrag;
            _OnWall = true;
            _WallNormal = hit.normal;
        } else {
            _Rb.drag = 0;
            _OnWall = false;
        }
    }

    // Gère le saut du personnage, ainsi que son animation de saut
    void CheckJump()
    {
        if (_Grounded)
        {
            if (Input.GetButtonDown("Jump"))
            {
                _Rb.AddForce(new Vector3(0, JumpForce, 0), ForceMode.Impulse);
                _Anim.SetBool("Grounded", false);
                _Anim.SetBool("Jump", true);
            }
        } 
        else if (_OnWall)
        {
            if (Input.GetButtonDown("Jump"))
            {
                Vector3 jump = new Vector3(0, JumpForce, 0) + _WallNormal * WallJumpForce;
                _Rb.AddForce(jump, ForceMode.Impulse);
                _Grounded = false;
                _Anim.SetBool("Grounded", false);
                _Anim.SetBool("Jump", true);
            }
        }
    }

    void CheckFire()
    {
        if (Input.GetButton("Fire1") && _Gun.CanFire() && !_IsGunEmpty)
        {
            _Gun.Fire();
            var munition = GetComponent<Munitions>();
            if(munition != null)
            {
                munition.PerdreBalle();
            }
            //gameObject.SendMessage("PerdreBalle");
        }
    }

    void CheckIfGrounded()
    {
        if (Physics.Raycast(transform.position, new Vector3(0, -1, 0), 0.1f, WhatIsGround))
        {
            _Grounded = true;
        }
        else
        {
            _Grounded = false;
        }
        _Anim.SetBool("Grounded", _Grounded);
    }

    void UpdateGunPosition(Vector3 mousePos)
    {
        Vector3 gunDirection = (mousePos - _ShoulderPoint.position).normalized;
        float distance = (_Gun.transform.position - _ShoulderPoint.position).magnitude;
        Quaternion newRot = Quaternion.LookRotation(gunDirection);
        Vector3 newPos = newRot * new Vector3(0.0f, 0.0f, distance) + _ShoulderPoint.position;

        _Gun.UpdatePosition(newPos, newRot);
        FlipCharacter(gunDirection.z);
    }

    // Gère l'orientation du joueur et les ajustements de la camera
    void FlipCharacter(float horizontal)
    {
        if (horizontal < 0 && !_Flipped)
        {
            _Flipped = true;
            transform.Rotate(FlipRotation);
            _MainCamera.transform.Rotate(-FlipRotation);
            _MainCamera.transform.localPosition = InverseCameraPosition;
        }
        else if (horizontal > 0 && _Flipped)
        {
            _Flipped = false;
            transform.Rotate(-FlipRotation);
            _MainCamera.transform.Rotate(FlipRotation);
            _MainCamera.transform.localPosition = CameraPosition;
        }
    }

    // Permet au joueur de lâcher le plafond s'il y est accroché
    void CheckCeilingHook()
    {
        var vertical = Input.GetAxis("Vertical") * MoveSpeed;
        var jump = Input.GetButtonDown("Jump");
        if (vertical < 0 || jump) {
            _Rb.useGravity = true;
            _IsHooked = false;
        }
    }

    // Collision avec le sol ou le plafond
    void OnCollisionEnter(Collision coll)
    {        
        // On peut s'accrocher à certains plafonds
        _IsHooked = CollideWithHookableCeiling(coll);
        _Rb.useGravity = !_IsHooked;

        // On s'assure de bien être en contact avec le sol
        if ((WhatIsGround & (1 << coll.gameObject.layer)) == 0)
            return;

        // Évite une collision avec le plafond
        if (coll.relativeVelocity.y > 0)
        {
            _Grounded = true;
            _Anim.SetBool("Grounded", _Grounded);
        }
    }

    void GameOver()
    {
        isGameOver = true;
    }
    
    void OnCollisionExit(Collision coll)
    {
        _IsHooked = CollideWithHookableCeiling(coll);
        _Rb.useGravity = !_IsHooked;
    }

    // Détecte les collisions avec le plafond
    bool CollideWithHookableCeiling(Collision coll)
    {
        return coll.gameObject.tag == "Can Hook" && CeilingCollisionIsValid(coll);
    }

    // Vérifie si une collision avec le plafond est valide
    bool CeilingCollisionIsValid(Collision coll) 
    {
        // On touche le plafond si un des points de contact est valide
        foreach (ContactPoint contact in coll.contacts)
            if (contact.normal == Vector3.down)
                return true;
        return false;
    }

    Vector3 MousePosToBasePlaneCoord(Vector3 mousePos)
    {
        Ray ray = _MainCamera.ScreenPointToRay(mousePos);
        RaycastHit hit;

        Physics.Raycast(ray.origin, ray.direction, out hit, Mathf.Infinity, WhatIsBasePlane);
        return hit.point;
    }

    IEnumerator AsyncUnfreezeAirControl(float time)
    {
        yield return new WaitForSeconds(time);
        _AirControl = true;
    }

    public void FreezeAirControl(float time)
    {
        float realTime = Mathf.Clamp(time, 0.0f, 10);
        _AirControl = false;
        StartCoroutine(AsyncUnfreezeAirControl(time));
    }

    public void FreezePlayer(bool freeze = true)
    {
        _FreezeControl = freeze;
        GetComponent<Rigidbody>().isKinematic = freeze;
    }

    private void BlockGun()
    {
        _IsGunEmpty = true;
    }

    private void UnblockGun()
    {
        _IsGunEmpty = false;
    }
}