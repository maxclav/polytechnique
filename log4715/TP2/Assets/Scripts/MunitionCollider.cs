using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MunitionCollider : MonoBehaviour {

    public void OnTriggerStay(Collider coll)
    {
        if (coll.tag == "Player")
        {
            coll.SendMessage("ReloadGun");
        }
    }
}
