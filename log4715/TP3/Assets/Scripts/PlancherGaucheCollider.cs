using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlancherGaucheCollider : MonoBehaviour {

    [SerializeField]
    float damageValue = 15f;

    public void OnTriggerStay(Collider coll)
    {
        if(coll.tag == "Player")
        {
            coll.SendMessage("DamagePlayer", Time.deltaTime * damageValue);
        }
    }
}
