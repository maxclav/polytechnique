using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class keyScript : MonoBehaviour {

	// Use this for initialization
	void Start () {
		
	}

    private void OnTriggerEnter(Collider coll)
    {
        if (coll.tag == "Player")
        {
            GameObject.FindWithTag("CanvasKey").GetComponentInChildren<Renderer>().enabled = true;
            Destroy(this.gameObject);
        }
    }
}
