using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class winCapsuleCollider : MonoBehaviour {

    public void OnTriggerStay(Collider coll)
    {
        if (coll.tag == "Player")
        {
            coll.SendMessage("WinGame");
            gameObject.SetActive(false);
        }
    }
}
