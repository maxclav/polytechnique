using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DamagingSurface : MonoBehaviour
{

    [SerializeField]
    [Range(0, Mathf.Infinity)]
    float TouchDamage = 5;

    [SerializeField]
    [Range(0, Mathf.Infinity)]
    float KnockbackForce = 300;

    Rigidbody _RB;
    GameObject _player;
    Rigidbody _playerRB;

    // Use this for initialization
    void Start()
    {
        _RB = GetComponent<Rigidbody>();
        _player = GameObject.FindWithTag("Player");
        _playerRB = _player.GetComponent<Rigidbody>();
    }

    private void OnCollisionEnter(Collision collision)
    {
        if (collision.collider.tag == "Player")
        {
            _player.SendMessage("DamagePlayer", TouchDamage);

            ContactPoint cp = collision.contacts[0];
            Vector3 feedbackDirection = -cp.normal + new Vector3(0, 1, 0);
            feedbackDirection = feedbackDirection.normalized;
            Debug.DrawLine(cp.point, cp.point + feedbackDirection * KnockbackForce, Color.red, 1);

            _playerRB.velocity = new Vector3(0, 0, _playerRB.velocity.z);
            _playerRB.AddForce(feedbackDirection * KnockbackForce);
        }
    }
}
