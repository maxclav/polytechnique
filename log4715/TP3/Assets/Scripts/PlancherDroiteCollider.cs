using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlancherDroiteCollider : MonoBehaviour {

    [SerializeField]
    float healingValue = 15f;

    public void OnTriggerStay(Collider coll)
    {
        if(coll.tag == "Player")
        {
            coll.SendMessage("HealPlayer", Time.deltaTime * healingValue);
        }
    }
}
