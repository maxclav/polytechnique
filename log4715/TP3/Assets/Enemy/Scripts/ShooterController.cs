using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ShooterController : MonoBehaviour {

    [SerializeField]
    [Range(0, Mathf.Infinity)]
    float TouchDamage = 5;

    [SerializeField]
    float ScoreValue = 35;

    [SerializeField]
    uint HealthPoint = 2;

    [SerializeField]
    [Range(0, Mathf.Infinity)]
    float KnockbackForce = 300;

    [SerializeField]
    [Range(0, Mathf.Infinity)]
    float ProjectileDamage = 10;

    [SerializeField]
    [Range(0, Mathf.Infinity)]
    float ProjectileSpeed = 5;

    [SerializeField]
    [Range(0, Mathf.Infinity)]
    float ProjectileFrequency = 2;

    [SerializeField]
    bool ShootAndJump = true;

    [SerializeField]
    [Range(0, Mathf.Infinity)]
    float JumpForce = 250.0f;

    [SerializeField]
    GameObject Projectile = null;

    [SerializeField]
    AudioClip Hurt = null;

    [SerializeField]
    AudioClip Death = null;

    [SerializeField]
    AudioClip Shoots = null;


    bool ignoreCollision = false;
    float shootTimer = 0.0f;
    bool _CanShoot = true;

    Animator _Anim;
    AudioSource _AS;

    void Awake()
    {
        _Anim = GetComponentInChildren<Animator>();
        _AS = GetComponent<AudioSource>();
    }

    // Use this for initialization
    void Start () 
    {
        
    }
    
    // Update is called once per frame
    void FixedUpdate () 
    {
        shootTimer += Time.fixedDeltaTime;
        _Anim.enabled = true;
        if(_CanShoot && shootTimer > ProjectileFrequency)
        {
            _AS.PlayOneShot(Shoots);

            ShooterProjectileControler o = Instantiate(Projectile, transform.position, transform.rotation).GetComponent<ShooterProjectileControler>();
            o.Initialize(transform.forward*-ProjectileSpeed, new Vector3(-ProjectileSpeed, 0, 0), ProjectileDamage);
            shootTimer = 0.0f;

            _Anim.enabled = false;

            if (ShootAndJump)
            {
                GetComponent<Rigidbody>().AddForce(new Vector3(0, JumpForce, 0));
            }
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
        }
        else if (collision.collider.tag == "Bullet" && !collision.gameObject.GetComponent<BulletMover>().CanCollideWithEnemies())
        {
            Physics.IgnoreCollision(collision.gameObject.GetComponent<MeshCollider>(), GetComponent<SphereCollider>());
        }
        else if (collision.collider.tag == "Bullet")
        {
            TakeDamage(1);

            Destroy(collision.gameObject);
        }
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
        else
        {
            _AS.PlayOneShot(Hurt);
        }
    }

    public void Kill()
    {
		GameObject.FindWithTag ("GameUI").SendMessage("addScore", ScoreValue);
        GetComponentInChildren<SpriteRenderer>().enabled = false;
        GetComponentInChildren<SphereCollider>().enabled = false;
        _AS.Stop();
        _AS.PlayOneShot(Death);
        _CanShoot = false;
        Destroy(gameObject, 1.0f);
    }

    IEnumerator AsyncCooldown(float time)
    {
        yield return new WaitForSeconds(time);
        ignoreCollision = false;
    }
}
