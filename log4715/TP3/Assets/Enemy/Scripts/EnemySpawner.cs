using System.Collections;
using System.Collections.Generic;
using UnityEngine;


public class EnemySpawner : MonoBehaviour
{
    public int numberOfEnnemies = 1;
    public GameObject enemyPrefab;
    public float spawnDistance = 10f;

    private bool _triggered = false;
    
    // Use this for initialization
    void Start()
    {
    }

    private void SpawnEnemies()
    {
        if (enemyPrefab != null)
        {
            for (int i = 0; i < numberOfEnnemies; i++)
            {
                Vector3 fwd = Vector3.forward;
                Vector3 step = spawnDistance > 0 ? fwd : -fwd;
                Instantiate(enemyPrefab, transform.position + fwd*spawnDistance + i*step, transform.rotation);
            }
        }
    }

    private void OnTriggerEnter(Collider collider)
    {
        if (!_triggered && collider.tag == "Player")
        {
            _triggered = true;
            SpawnEnemies();
        }
    }
}
