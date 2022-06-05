using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class lavaCollider : MonoBehaviour {

    public void OnTriggerEnter(Collider coll)
    {
        if (coll.tag == "Player")
        {
            coll.transform.position = new Vector3(0.0f, 0.0f, 0.0f);
            coll.GetComponent<Rigidbody>().velocity = new Vector3(0.0f, 0.0f, 0.0f);
        }
    }
}
