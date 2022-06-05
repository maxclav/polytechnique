using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BulletMover : MonoBehaviour {

    public float speed = 8;
    public float maximumDistance = 1200; // 2 secondes

    private float currentDistance = 0;
	// Use this for initialization
	void Start () {
        GetComponent<Rigidbody>().velocity = transform.forward * speed;
	}
	
	// Update is called once per frame
	void Update () {
        currentDistance += speed;
        if(currentDistance > maximumDistance)
        {
            DestroyObject(transform.parent.gameObject);
        }
	}

    void OnTriggerEnter(Collider other)
    {
        if(other.tag != "Player" && other.tag != "BasePlane" && other.tag != "Rope")
        {
            if(other.tag == "Explosive")
            {
                other.SendMessage("gasBottleHit");
            }
            Destroy(transform.parent.gameObject);
        }
    }
}
