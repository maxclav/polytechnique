using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class doorScript : MonoBehaviour {

    void OnTriggerEnter(Collider coll)
    {
        if (coll.tag == "Player")
        {
            GameObject thedoor = GameObject.FindWithTag("SF_Door");
            GameObject key = GameObject.FindWithTag("Key");
            if (key == null)
            {
                thedoor.GetComponent<Animation>().Play("open");
            }
        }
    }

    void OnTriggerExit(Collider coll)
    {
        if (coll.tag == "Player")
        {
            GameObject thedoor = GameObject.FindWithTag("SF_Door");
            GameObject key = GameObject.FindWithTag("Key");
            if (key == null)
            {
                thedoor.GetComponent<Animation>().Play("close");
            }
        }
    }
}
