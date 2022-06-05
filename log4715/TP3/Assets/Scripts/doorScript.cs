using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class doorScript : MonoBehaviour {

    void OnTriggerEnter(Collider coll)
    {
        
        if (coll.tag == "Player")
        {
            if (this.gameObject.name.Contains("Gold"))
            {
                if (GameObject.FindWithTag("Player").GetComponent<PlayerInventoryScript>().hasGoldKey)
                {
                    this.gameObject.gameObject.GetComponentInChildren<Animation>().Play("open");
                }
                else
                {
                    GameObject.FindWithTag("GameUI").GetComponent<UIControllerScript>().SendMessage("KeyMessage", "Or");
                }
            }
            else
            {
                if (GameObject.FindWithTag("Player").GetComponent<PlayerInventoryScript>().hasSilverKey)
                {
                    this.gameObject.gameObject.GetComponentInChildren<Animation>().Play("open");
                }
                else
                {
                    GameObject.FindWithTag("GameUI").GetComponent<UIControllerScript>().SendMessage("KeyMessage", "Argent");
                }
            }
        }
    }

    void OnTriggerExit(Collider coll)
    {
        if (coll.tag == "Player")
        {
            if (this.gameObject.name.Contains("Gold"))
            {
                if (GameObject.FindWithTag("Player").GetComponent<PlayerInventoryScript>().hasGoldKey)
                {
                    this.gameObject.gameObject.GetComponentInChildren<Animation>().Play("close");
                }
            }
            else
            {
                if (GameObject.FindWithTag("Player").GetComponent<PlayerInventoryScript>().hasSilverKey)
                {
                    this.gameObject.gameObject.GetComponentInChildren<Animation>().Play("close");
                }
            }
        }
    }
}
