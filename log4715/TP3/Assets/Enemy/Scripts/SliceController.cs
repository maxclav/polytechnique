using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SliceController : MonoBehaviour
{
    [SerializeField]
    float ScoreValue = 5;

    [SerializeField]
    [Range(0, Mathf.Infinity)]
    float TouchDamage = 5;

    [SerializeField]
    [Range(0, Mathf.Infinity)]
    float Speed = 5;

    [SerializeField]
    uint HealthPoint = 1;

    [SerializeField]
    [Range(0, Mathf.Infinity)]
    float KnockbackForce = 300;

    [SerializeField]
    AudioClip Death = null;

    Rigidbody _RB;
    AudioSource _AS;
    bool ignoreCollision = false;
    bool _CanTurn = true;

    bool _FreezeEntity = false;
    CapsuleCollider _Collider;

    public void FreezeEntity(bool freeze = true)
    {
        _FreezeEntity = freeze;
    }

    public void AsyncFreezeEntity(float time)
    {
        _FreezeEntity = true;
        _Collider.enabled = false;
        _RB.velocity = new Vector3(0, 0, 0);
        StartCoroutine(AsyncUnfreeze(time));
    }

    public void AsyncIgnoreColision(float time)
    {
        ignoreCollision = true;
        StartCoroutine(AsyncCooldown(time));
    }

    public void InitializeVelocity()
    {
        Vector3 newVelocity = new Vector3
        {
            x = 0,
            y = _RB.velocity.y,
            z = Speed * transform.forward.z
        };

        _RB.velocity = newVelocity;
    }

    void Awake()
    {
        _RB = GetComponent<Rigidbody>();
        _Collider = GetComponentInChildren<CapsuleCollider>();
        _AS = GetComponent<AudioSource>();
    }

    // Use this for initialization
    void Start()
    {
        if(!_FreezeEntity)
        {
            InitializeVelocity();
        }
        
    }

    // Update is called once per frame
    void FixedUpdate()
    {
        GameObject player = GameObject.FindWithTag("Player");
        Vector3 direction = player.transform.position - transform.position;
        direction.x = 0;
        direction.y = 0;
        direction = direction.normalized;

        if (_CanTurn && transform.forward != direction)
        {
            transform.Rotate(new Vector3(0, 180, 0));
            
            _CanTurn = false;
            StartCoroutine(AsyncTurnCooldown(0.1f));
        }

        if (!_FreezeEntity)
        {
            InitializeVelocity();
        }
        
    }

    private void OnCollisionEnter(Collision collision)
    {
        if (collision.collider.tag == "Player" && ignoreCollision == false)
        {
            GameObject player = GameObject.FindWithTag("Player");
            player.SendMessage("DamagePlayer", TouchDamage);

            ContactPoint cp = collision.contacts[0];
            Vector3 feedbackDirection = -cp.normal + new Vector3(0, 1, 0);
            feedbackDirection = feedbackDirection.normalized;
            Debug.DrawLine(cp.point, cp.point + feedbackDirection * KnockbackForce, Color.red, 1);

            player.GetComponent<Rigidbody>().velocity = new Vector3(0, 0, player.GetComponent<Rigidbody>().velocity.z);
            player.GetComponent<Rigidbody>().AddForce(feedbackDirection * KnockbackForce);

            StartCoroutine(AsyncCooldown(0.5f));
            ignoreCollision = true;

            Kill();
        }
        else if (collision.collider.tag == "Bullet")
        {
            TakeDamage(1);

            Destroy(collision.gameObject);
        }
    }

    void Kill()
    {
		GameObject.FindWithTag ("GameUI").GetComponent<UIControllerScript>().SendMessage("addScore", ScoreValue);
        GetComponentInChildren<ParticleSystem>().Play();
        GetComponentInChildren<SpriteRenderer>().enabled = false;
        _Collider.enabled = false;
        _AS.PlayOneShot(Death);
        Destroy(gameObject, 1.0f);
    }

    public void TakeDamage(uint damage)
    {

		if(damage>= HealthPoint)
		{
			Kill();
			return;
		}

        HealthPoint -= damage;

        if (HealthPoint <= 0)
        {
            Kill();
        }
    }

    IEnumerator AsyncCooldown(float time)
    {
        yield return new WaitForSeconds(time);
        ignoreCollision = false;
    }

    IEnumerator AsyncTurnCooldown(float time)
    {
        yield return new WaitForSeconds(time);
        _CanTurn = true;
    }

    IEnumerator AsyncUnfreeze(float time)
    {
        yield return new WaitForSeconds(time);
        _Collider.enabled = true;
        _FreezeEntity = false;
    }
}
