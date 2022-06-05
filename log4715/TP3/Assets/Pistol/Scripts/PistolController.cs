using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PistolController : MonoBehaviour {

    [SerializeField]
    public GameObject projectile;

    [SerializeField]
    [Range(0, Mathf.Infinity)]
    public float fireRate = 0.5f;

    private float nextFire = 0.0f;
    private float fireRateTime = 0.0f;
    private bool isColliding = false;

    Transform _ShotSpawn;

    void Awake()
    {
        _ShotSpawn = transform.Find("ShotSpawn");
    }

    // Use this for initialization
    void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
        fireRateTime += Time.deltaTime;
    }

    public void Fire()
    {
        if (CanFire())
        {
            nextFire = fireRateTime + fireRate;

            Instantiate(projectile, _ShotSpawn.position, transform.rotation);

            nextFire = nextFire - fireRateTime;
            fireRateTime = 0.0F;
        }
    }

    public bool CanFire()
    {
        if (fireRateTime >= nextFire && !isColliding)
        {
            return true;
        }
        return false;
    }

    public void UpdatePosition(Vector3 position, Quaternion rotation)
    {
        transform.position = position;
        transform.rotation = rotation;
    }

    public void SetIsColliding(bool colliding)
    {
        isColliding = colliding;
    }
}
