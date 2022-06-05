using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Rope : MonoBehaviour {

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		
	}

	void OnTriggerEnter(Collider coll)
    {        
		if (coll.tag == "Bullet") {
			Destroy(GetComponent<FixedJoint>());
			// Destroy(GetComponent<Rigidbody>());
			Destroy(this.gameObject);
		}
    }
}
