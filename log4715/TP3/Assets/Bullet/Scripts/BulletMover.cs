using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BulletMover : MonoBehaviour {

    [Range(0,Mathf.Infinity)]
    public float speed = 8;
    [Range(0,Mathf.Infinity)]
    public float maxAngularVelocity = 50.0f;

    [SerializeField]
    AudioClip Hit = null;

    private MeshCollider _meshCollider;
    private Rigidbody _rb;
    private GameObject _player;
    private bool _alreadyCollidedEnemy = false;
    private AudioSource _AS;

    private void Awake()
    {
        _AS = GetComponent<AudioSource>();
    }

    // Use this for initialization
    void Start () {
        _meshCollider = GetComponent<MeshCollider>();
        _rb = GetComponent<Rigidbody>();
        _player = GameObject.FindWithTag("Player");

        // As of my understanding, 2D sprites go from left to right,
        // not from back to forward (once rotated accordingly).
        _rb.velocity = transform.right * speed;
        _rb.maxAngularVelocity = maxAngularVelocity;
        _rb.AddRelativeTorque(Vector3.forward * Random.Range(-maxAngularVelocity, maxAngularVelocity));

        // Ignore collision between bullets and the player, who has 2 colliders
        Physics.IgnoreCollision(_player.GetComponent<BoxCollider>(), _meshCollider);
        Physics.IgnoreCollision(_player.GetComponent<SphereCollider>(), _meshCollider);
	}
	
	// Update is called once per frame
	void Update () {
        if (Mathf.Abs(_rb.velocity.magnitude) == 0 && Mathf.Abs(_rb.angularVelocity.magnitude) == 0)
        {
            StopCollidingWithEnemies();            
            Destroy(this.gameObject);
        }
	}

    public bool CanCollideWithEnemies()
    {
        return !_alreadyCollidedEnemy;
    }

    void StopCollidingWithEnemies()
    {
        _alreadyCollidedEnemy = true;
    }

    void OnTriggerEnter(Collider other)
    {
        if (other.tag == "Bullet")
        {
            // Collide with other bullets at most once (to prevent weird bullet piles)
            Physics.IgnoreCollision(other.gameObject.GetComponent<MeshCollider>(), _meshCollider);
        }
        else if(other.tag != "Player" && other.tag != "BasePlane" && other.tag != "Rope")
        {
            _AS.PlayOneShot(Hit);
            if(other.tag == "Explosive")
            {
                other.SendMessage("gasBottleHit");
            }
        }
    }
}
