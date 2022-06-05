using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ShooterProjectileControler : MonoBehaviour {

    [SerializeField]
    [Range(0.0f, Mathf.Infinity)]
    float MaxDistance = 1200.0f;

    [SerializeField]
    [Range(0.0f, Mathf.Infinity)]
    float Damage = 15;

    float CurrentDistance = 0.0f;

    Vector3 lastPosition = new Vector3();
    bool ignoreCollision = false;
    
    public void Initialize(Vector3 speed, Vector3 angularSpeed, float damage)
    {
        GetComponent<Rigidbody>().velocity = speed;
        GetComponent<Rigidbody>().angularVelocity = angularSpeed;
        Damage = damage;
        lastPosition = transform.position;
    }

    // Use this for initialization
    void Start () {

    }

    void FixedUpdate()
    {
        CurrentDistance += (transform.position - lastPosition).magnitude;
        lastPosition = transform.position;
        CurrentDistance += GetComponent<Rigidbody>().velocity.magnitude;
        if (CurrentDistance >= MaxDistance)
        {
            Destroy(gameObject);
        }
    }

    // Update is called once per frame
    void Update () 
    {

    }

    void OnTriggerEnter(Collider other)
    {
        if (other.tag == "Player" && !ignoreCollision)
        {
            GameObject player = GameObject.FindWithTag("Player");
            player.SendMessage("DamagePlayer", Damage);
            Destroy(gameObject);
            player.GetComponent<Rigidbody>().AddForce(new Vector3(0, 0, -150));
        }
    }
}
