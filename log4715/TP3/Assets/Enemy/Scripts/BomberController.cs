using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BomberController : MonoBehaviour
{
    [SerializeField]
    float ScoreValue = 50;

    [SerializeField]
    [Range(0, Mathf.Infinity)]
    float TouchDamage = 10.0f;

    [SerializeField]
    [Range(0, Mathf.Infinity)]
    float Speed = 0.5f;

    [SerializeField]
    uint HealthPoint = 2;

    [SerializeField]
    [Range(0, Mathf.Infinity)]
    float KnockbackForce = 300;

    [SerializeField]
    uint NumberOfSlice = 4;

    [SerializeField]
    GameObject SlicePrefab = null;

    [SerializeField]
    AudioClip Hurt = null;

    [SerializeField]
    AudioClip Death = null;

    [SerializeField]
    AudioClip SliceSpawn = null;

    Rigidbody _RB;
    SphereCollider _Collider;
    AudioSource _AS;
    bool ignoreCollision = false;

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
        _Collider = GetComponentInChildren<SphereCollider>();
        _AS = GetComponent<AudioSource>();
    }

    // Use this for initialization
    void Start()
    {
        GameObject player = GameObject.FindWithTag("Player");
        Vector3 direction = player.transform.position - transform.position;
        direction.x = 0;
        direction.y = 0;
        direction = direction.normalized;

        _RB.velocity = Speed * direction;
    }

    // Update is called once per frame
    void Update()
    {
        float detectionRadius = _Collider.radius + 0.05f;
        Vector3 foward = transform.forward;
        LayerMask mask = LayerMask.GetMask("Floor", "Enemy") | LayerMask.GetMask("EnemyStopper", "Enemy");

        if (Physics.Raycast(transform.position, foward, detectionRadius, mask))
        {
            transform.Rotate(new Vector3(0, 180, 0));
        }

        InitializeVelocity();
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
            // HealthPoint--;

            // if (HealthPoint <= 0)
            // {
            //     GetComponentInChildren<ParticleSystem>().Play();
            //     GetComponentInChildren<SpriteRenderer>().enabled = false;
            //     GetComponentInChildren<SphereCollider>().enabled = false;
            //     Destroy(gameObject, 1.0f);
            //     SpawnSlices();
            // }
            TakeDamage(1);
            Destroy(collision.gameObject);
        }
    }

    void SpawnSlices()
    {
        // NE PAS TOUCHER
        // les directions sont très bizzare, les valeurs sont expérimenrales
        //float dz = 1.0f / NumberOfSlice;
        //float z = 0.5f;// * transform.forward.z;
        float angle = 45.0f;
        float dAngle = angle / NumberOfSlice;
        Vector3 direction = (Quaternion.Euler(-90 + angle/2 - dAngle/2, 0, 0) * new Vector3(0,0,1)).normalized;
        


        for (int i = 0; i < NumberOfSlice; i++)
        {
            Debug.DrawRay(transform.position, direction, Color.red, 2);
            GameObject slice = Instantiate(SlicePrefab, transform.position, transform.rotation);
            slice.GetComponent<SliceController>().AsyncFreezeEntity(1f);
            slice.GetComponent<Rigidbody>().velocity = new Vector3(0,0,0);
            //Vector3 force = new Vector3(0, 1, z);

            //slice.GetComponent<Rigidbody>().AddForce(25 * force, ForceMode.Impulse);
            slice.GetComponent<Rigidbody>().AddForce(35*direction, ForceMode.Impulse);
            direction = (Quaternion.Euler(-dAngle, 0, 0) * direction).normalized;
            //z += dz;
        }
       //SliceController slice = Instantiate(SlicePrefab, transform.position, transform.rotation).GetComponent<SliceController>();
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
        GetComponentInChildren<ParticleSystem>().Play();
        GetComponentInChildren<SpriteRenderer>().enabled = false;
        GetComponentInChildren<SphereCollider>().enabled = false;
        _AS.PlayOneShot(Death);
        _AS.PlayOneShot(SliceSpawn);
        Destroy(gameObject, 1.0f);
        SpawnSlices();
    }

    IEnumerator AsyncCooldown(float time)
    {
        yield return new WaitForSeconds(time);
        ignoreCollision = false;
    }
}
