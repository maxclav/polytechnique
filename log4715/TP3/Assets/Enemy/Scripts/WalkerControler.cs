using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class WalkerControler : MonoBehaviour {

    [SerializeField]
    float ScoreValue = 15;

    [SerializeField]
    [Range(0, Mathf.Infinity)]
    float TouchDamage = 5;

    [SerializeField]
    [Range(0, Mathf.Infinity)]
    float Speed = 5;

    [SerializeField]
    uint HealthPoint = 2;

    [SerializeField]
    [Range(0, Mathf.Infinity)]
    float KnockbackForce = 300;

    [SerializeField]
    AudioClip Hurt = null;

    [SerializeField]
    AudioClip Death = null;

    Rigidbody _RB;
    AudioSource _AS;
    bool ignoreCollision = false;

    void Awake()
    {
        _RB = GetComponent<Rigidbody>();
        _AS = GetComponent<AudioSource>();
    }

    // Use this for initialization
    void Start ()
    {
        _RB.velocity = transform.right*Speed;
    }
    
    // Update is called once per frame
    void Update ()
    {
        float detectionRadius = GetComponent<SphereCollider>().radius + 0.05f;
        Vector3 foward = transform.right;
        LayerMask mask = LayerMask.GetMask("Floor", "Enemy") | LayerMask.GetMask("EnemyStopper", "Enemy");
        if (Physics.Raycast(transform.position, foward, detectionRadius, mask))
        {
            transform.Rotate(new Vector3(0, 180,0));
            _RB.velocity = foward * Speed;
        }

        if(_RB.velocity.magnitude < Speed)
        {
            _RB.velocity = foward * Speed;
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
        else if(collision.collider.tag == "Bullet")
        {
            HealthPoint--;

            if(HealthPoint <= 0)
            {
				GameObject.FindWithTag ("GameUI").GetComponent<UIControllerScript>().SendMessage("addScore", 50);
                _AS.PlayOneShot(Death);
                GetComponentInChildren<SpriteRenderer>().enabled = false;
                GetComponentInChildren<SphereCollider>().enabled = false;
                Destroy(gameObject, 1.0f);
            }
            else
            {
                _AS.PlayOneShot(Hurt);
            }
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
		GameObject.FindWithTag ("GameUI").GetComponent<UIControllerScript>().SendMessage("addScore", ScoreValue);
        GetComponentInChildren<SpriteRenderer>().enabled = false;
        GetComponentInChildren<SphereCollider>().enabled = false;
        _AS.PlayOneShot(Death);
        Destroy(gameObject, 1.0f);
    }

    IEnumerator AsyncCooldown(float time)
    {
        yield return new WaitForSeconds(time);
        ignoreCollision = false;
    }
}
