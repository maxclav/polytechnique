using System.Collections;
using System.Collections.Generic;
using UnityEngine;


public class EnemyDespawner : MonoBehaviour
{
    // Use this for initialization
    void Start()
    {
    }

    private void OnTriggerEnter(Collider collider)
    {
        Debug.Log("OnTrigger!");
        if (collider.tag == "Enemy")
        {
            Debug.Log("Enemy!");
            DestroyObject(collider.gameObject);
        }
    }
}
