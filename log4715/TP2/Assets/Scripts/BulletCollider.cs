using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BulletCollider : MonoBehaviour {

    private bool isBulletTaken;

    public GameObject bullet;
    float timerRespawnBullet = 30f;
    private float timerRespawnBulletRemaining;

    void Start()
    {
        isBulletTaken = false;
        timerRespawnBulletRemaining = timerRespawnBullet + 0.99F;
    }

    void Update()
    {
        if (isBulletTaken)
        {
            timerRespawnBullet -= Time.deltaTime;

            if (timerRespawnBullet <= 1)
            {
                bullet.active = true;
                isBulletTaken = false;
                timerRespawnBulletRemaining = timerRespawnBullet + 0.99F;
            }
        }
    }
}
