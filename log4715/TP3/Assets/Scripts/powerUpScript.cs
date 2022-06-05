using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class powerUpScript : MonoBehaviour {

    public Text Alpha1;
    public Text Alpha2;
    public Text Alpha3;

    // Use this for initialization
    void Start () {
		
	}

    private void OnTriggerEnter(Collider coll)
    {
        
        PlayerInventoryScript inventory = GameObject.FindWithTag("Player").GetComponent<PlayerInventoryScript>();
        if (coll.tag == "Player")
        {
            if (this.gameObject.name.Contains("Shield"))
            {
                if (!inventory.hasShield)
                {
                    inventory.hasShield = true;
                    Destroy(this.gameObject);
                }
            }

            if (this.gameObject.name.Contains("Nuke"))
            {
                if (!inventory.hasNuke)
                {
                    inventory.hasNuke = true;
                    Destroy(this.gameObject);
                }
            }

            if (this.gameObject.name.Contains("Health"))
            {
                if (!inventory.hasHealth)
                {
                    inventory.hasHealth = true;
                    Destroy(this.gameObject);
                }
            }
        }
    }
}
