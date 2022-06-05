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
            if (this.gameObject.name.Contains("Gold"))
            {
                GameObject.FindWithTag("Player").GetComponent<PlayerInventoryScript>().hasGoldKey = true;
            }
            else
            {
                GameObject.FindWithTag("Player").GetComponent<PlayerInventoryScript>().hasSilverKey = true;
            }

            Destroy(this.gameObject);
        }
    }
}
