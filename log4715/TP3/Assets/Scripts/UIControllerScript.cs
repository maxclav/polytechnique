using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;
using System.IO;
using System.Linq;

public class UIControllerScript : MonoBehaviour
{

    public Transform canvasMenuPrincipal;
    public Transform canvasHUD;
    public Transform canvasInGame;
    public Transform canvasBoards;
    public Transform canvasEnding;
    public Transform canvasGameOver;

    public Transform timer;
    public Transform score;
    public Transform keyMessage;

    public Transform tempsFinal;
    public Transform tempsRestant;
    public Transform scoreFinal;

    public Transform inputName;
    public Transform saveButton;

    public Transform nameScoresBoard;
    public Transform scoreScoresBoard;
    public Transform nameTimesBoard;
    public Transform timeTimessBoard;

    public Transform nukePowerUp;
    public Transform shieldPowerUp;
    public Transform healthPowerUp;
    public Transform silverKey;
    public Transform goldKey;

    public Slider healthPointsSlider;
    public Text healthPointsRatioText;

    bool startingGame = false;
    bool endingGame = false;

    bool gameEnded = false;
    bool gamePaused = false;
    bool gameStarted = false;

    bool keyMessageBool = false;
    float keyTime = 0.0f; 

    public float tempsMaximal = 600.0f;
    float timeLeft;
    float tempsRequis = 0.0f;

	string cheatcode;


    public class MyScoreClass
    {
        public string name { get; set; }
        public int score { get; set; }

        public MyScoreClass(string name_, int score_)
        {
            name = name_;
            score = score_;
        }
    }

    public class MyTimeClass
    {
        public string name;
        public float time;

        public MyTimeClass(string name_, float time_)
        {
            name = name_;
            time = time_;
        }
    }

    // Use this for initialization
    void Start()
    {

        //met le jeu sur pause
        Time.timeScale = 0;
        GameObject.FindWithTag("Player").GetComponent<PlayerControler>().enabled = false;


        //deplace cam
        GameObject cam = GameObject.FindWithTag("MainCamera");
        cam.GetComponent<Camera>().fieldOfView = 175;
        cam.GetComponent<Transform>().position = new Vector3(10, -1, 120);

        timeLeft = tempsMaximal;

        UnityEngine.EventSystems.EventTrigger[] triggers = saveButton.GetComponents<UnityEngine.EventSystems.EventTrigger>();
        foreach (UnityEngine.EventSystems.EventTrigger trigger in triggers)
        {
            trigger.enabled = false;
        }

        this.gameObject.tag = "GameUI";

    }

    // Update is called once per frame
    void Update()
    {
        //starting game camera animation
        StartingGameAnimation();

        //Ending Menu Animation
        FinalScoreAnimation();

        //Inventory controller
        ShowOrHideInventoy();
        

        //Toggle ingame Menu
        if (Input.GetKeyDown(KeyCode.Escape))
        {
            if (gameStarted)
            {
                if (gamePaused)
                {
                    ResumeGame();
                }
                else
                {
                    PauseGame();
                }
            }
        }

        //Timer countdown
        if (gameStarted && !gamePaused)
        {
            HealthUpdate();

            TimeUpdate();

			if (Input.anyKeyDown) 
			{
				foreach (char c in Input.inputString) 
				{
					if (c >= 'a' || c <= 'z') 
					{
						cheatcode += Input.inputString;
					}
				}

				if (cheatcode.Length > 100) 
				{
					cheatcode = "";
				}
				if (cheatcode.Contains ("victory")) 
				{
					EndGame();
					cheatcode = "";
				}
				if (cheatcode.Contains ("invincible")) 
				{
					GameObject.FindWithTag ("Player").GetComponent<PlayerInventoryScript> ().invincible = !GameObject.FindWithTag ("Player").GetComponent<PlayerInventoryScript> ().invincible;
					cheatcode = "";
				}
			}
        }

        if (keyMessageBool)
        {
            keyTime += Time.deltaTime;
            if (keyTime >= 3.0f)
            {
                keyTime = 0.0f;
                keyMessageBool = false;
                keyMessage.GetComponent<Text>().text = "";
            }
        }
    }


    public void StartGame()
    {
        startingGame = true;

        HideCanvas(canvasMenuPrincipal);
        ShowCanvas(canvasHUD);

    }

    public void ExitGame()
    {
        Application.Quit();
    }

    public void PauseGame()
    {
        gamePaused = true;

        Time.timeScale = 0;
        HideCanvas(canvasHUD);
        ShowCanvas(canvasInGame);

        GameObject.FindWithTag("Player").GetComponent<PlayerControler>().enabled = false;
    }

    public void ResumeGame()
    {
        gamePaused = false;

        Time.timeScale = 1;
        HideCanvas(canvasInGame);
        ShowCanvas(canvasHUD);

        GameObject.FindWithTag("Player").GetComponent<PlayerControler>().enabled = true;
    }

    public void GameOver()
    {
        gameStarted = false;
        HideCanvas(canvasHUD);
        ShowCanvas(canvasGameOver);

        Time.timeScale = 0;
        GameObject.FindWithTag("Player").GetComponent<PlayerControler>().enabled = true;
    }


    void EndGame()
    {
		this.gameObject.GetComponent<AudioSource>().Play();
        gameStarted = false;
        HideCanvas(canvasHUD);
        ShowCanvas(canvasEnding);

        Time.timeScale = 0;
        GameObject.FindWithTag("Player").GetComponent<PlayerControler>().enabled = false;

        tempsRestant.GetComponent<Text>().text = timer.GetComponent<Text>().text;

        scoreFinal.GetComponent<Text>().text = score.GetComponent<Text>().text;

        tempsRequis = tempsMaximal - timeLeft;
        string minutes = Mathf.Floor(tempsRequis / 60).ToString("00");
        string seconds = Mathf.Floor(tempsRequis % 60).ToString("00");
        tempsFinal.GetComponent<Text>().text = minutes.ToString() + " : " + seconds.ToString();

        endingGame = true;
    }

    public void MainMenu()
    {
        SceneManager.LoadScene(SceneManager.GetActiveScene().name);
    }

    public void saveScore()
    {
        string path = Path.GetDirectoryName(Application.streamingAssetsPath) + "/StreamingAssets/Scores.txt";

        using (StreamWriter sw = File.AppendText(path))
        {
            sw.WriteLine(inputName.GetComponent<InputField>().text + ":" + scoreFinal.GetComponent<Text>().text);
        }

        path = Path.GetDirectoryName(Application.streamingAssetsPath) + "/StreamingAssets/Times.txt";
        using (StreamWriter sw = File.AppendText(path))
        {
            sw.WriteLine(inputName.GetComponent<InputField>().text + ":" + Mathf.Floor(tempsRequis).ToString());
        }

        LeaderBoards();
    }


    public void LeaderBoards()
    {
        List<MyScoreClass> scores = new List<MyScoreClass>();
        List<MyTimeClass> times = new List<MyTimeClass>();

        string path = Path.GetDirectoryName(Application.streamingAssetsPath) + "/StreamingAssets/Scores.txt";
        string[] lines = System.IO.File.ReadAllLines(path);
        foreach (string line in lines)
        {
            string[] array = line.Split(new char[] { ':' }, 2);
            MyScoreClass score = new MyScoreClass(array[0], int.Parse(array[1]));
            scores.Add(score);
        }

        path = Path.GetDirectoryName(Application.streamingAssetsPath) + "/StreamingAssets/Times.txt";
        lines = System.IO.File.ReadAllLines(path);
        foreach (string line in lines)
        {
            string[] array = line.Split(new char[] { ':' }, 2);
            MyTimeClass time = new MyTimeClass(array[0], float.Parse(array[1]));
            times.Add(time);
        }

        scores = scores.OrderByDescending(o => o.score).ToList();
        times = times.OrderBy(o => o.time).ToList();
        
        for (int i=0;i<10 && i<scores.Count();i++)
        {
            nameScoresBoard.GetComponent<Text>().text = nameScoresBoard.GetComponent<Text>().text + scores[i].name +"\n";
            scoreScoresBoard.GetComponent<Text>().text = scoreScoresBoard.GetComponent<Text>().text + scores[i].score + "\n";
        }

        for (int i = 0; i < 10 && i < times.Count(); i++)
        {
            nameTimesBoard.GetComponent<Text>().text = nameTimesBoard.GetComponent<Text>().text + times[i].name + "\n";
            string minutes = Mathf.Floor(times[i].time / 60).ToString("00");
            string seconds = Mathf.Floor(times[i].time % 60).ToString("00");
            timeTimessBoard.GetComponent<Text>().text = timeTimessBoard.GetComponent<Text>().text + minutes + " : " + seconds + "\n";
        }

        HideCanvas(canvasMenuPrincipal);
        HideCanvas(canvasEnding);
        ShowCanvas(canvasBoards);
    }

    void ShowCanvas(Transform canvas)
    {
        CanvasGroup canvasGroup = canvas.GetComponent<CanvasGroup>();
        canvasGroup.alpha = 1;
        canvasGroup.interactable = true;
        canvasGroup.blocksRaycasts = true;
    }

    void HideCanvas(Transform canvas)
    {
        CanvasGroup canvasGroup = canvas.GetComponent<CanvasGroup>();
        canvasGroup.alpha = 0;
        canvasGroup.interactable = false;
        canvasGroup.blocksRaycasts = false;
    }

    public void addScore(int addingScore)
    {
        score.GetComponent<Text>().text = (int.Parse(score.GetComponent<Text>().text)+addingScore).ToString();
    }

    void ShowOrHideInventoy()
    {
        if (gameStarted)
        {
            PlayerInventoryScript player = GameObject.FindWithTag("Player").GetComponent<PlayerInventoryScript>();

            if (player.hasNuke)
            {
                nukePowerUp.GetComponentInChildren<Renderer>().enabled = true;
            }
            else
            {
                nukePowerUp.GetComponentInChildren<Renderer>().enabled = false;
            }

            if (player.hasShield)
            {
                shieldPowerUp.GetComponentInChildren<Renderer>().enabled = true;
            }
            else
            {
                shieldPowerUp.GetComponentInChildren<Renderer>().enabled = false;
            }

            if (player.hasHealth)
            {
                healthPowerUp.GetComponentInChildren<Renderer>().enabled = true;
            }
            else
            {
                healthPowerUp.GetComponentInChildren<Renderer>().enabled = false;
            }

            if (player.hasSilverKey)
            {
                silverKey.GetComponentInChildren<Renderer>().enabled = true;
            }
            else
            {
                silverKey.GetComponentInChildren<Renderer>().enabled = false;
            }

            if (player.hasGoldKey)
            {
                goldKey.GetComponentInChildren<Renderer>().enabled = true;
            }
            else
            {
                goldKey.GetComponentInChildren<Renderer>().enabled = false;
            }
        }
    }


    void FinalScoreAnimation()
    {
        if (endingGame)
        {
            if (timeLeft >= 10.0f)
            {
                scoreFinal.GetComponent<Text>().text = (int.Parse(scoreFinal.GetComponent<Text>().text) + 100).ToString();
                timeLeft -= 10.0f;
                string minutes = Mathf.Floor(timeLeft / 60).ToString("00");
                string seconds = Mathf.Floor(timeLeft % 60).ToString("00");
                tempsRestant.GetComponent<Text>().text = minutes.ToString() + " : " + seconds.ToString();
            }
            else if (timeLeft > 0.0f && timeLeft < 10.0f)
            {
                scoreFinal.GetComponent<Text>().text = (int.Parse(scoreFinal.GetComponent<Text>().text) + 10).ToString();
                timeLeft -= 1.0f;
                string minutes = Mathf.Floor(timeLeft / 60).ToString("00");
                string seconds = Mathf.Floor(timeLeft % 60).ToString("00");
            }
            else
            {
                endingGame = false;
                gameEnded = true;
                inputName.GetComponent<InputField>().interactable = true;
                tempsRestant.GetComponent<Text>().text = "00 : 00";
            }
        }

        if (gameEnded)
        {

            if (inputName.GetComponent<InputField>().text.Length >= 3)
            {
                saveButton.GetComponent<Button>().interactable = true;
                UnityEngine.EventSystems.EventTrigger[] triggers = saveButton.GetComponents<UnityEngine.EventSystems.EventTrigger>();
                foreach (UnityEngine.EventSystems.EventTrigger trigger in triggers)
                {
                    trigger.enabled = true;
                }
            }
            else
            {
                saveButton.GetComponent<Button>().interactable = false;
                UnityEngine.EventSystems.EventTrigger[] triggers = saveButton.GetComponents<UnityEngine.EventSystems.EventTrigger>();
                foreach (UnityEngine.EventSystems.EventTrigger trigger in triggers)
                {
                    trigger.enabled = false;
                }
            }
        }
    }

    void StartingGameAnimation()
    {
        if (startingGame)
        {
            GameObject cam = GameObject.FindWithTag("MainCamera");

            if (cam.GetComponent<Camera>().fieldOfView > 60)
            {
                cam.GetComponent<Camera>().fieldOfView = cam.GetComponent<Camera>().fieldOfView - 1f;
            }
            if (cam.GetComponent<Transform>().position.z > 0)
            {
                cam.GetComponent<Transform>().position = new Vector3(10, -1, cam.GetComponent<Transform>().position.z - 1f);
            }
            if (cam.GetComponent<Camera>().fieldOfView == 60 && cam.GetComponent<Transform>().position.z == 0)
            {
                startingGame = false;
                gameStarted = true;
                gamePaused = false;
                Time.timeScale = 1;
                GameObject.FindWithTag("Player").GetComponent<PlayerControler>().enabled = true;
                GameObject.FindWithTag("Player").GetComponent<PlayerControler>().SendMessage("FlipCharacter", -1.0f);
                GameObject.FindWithTag("Player").GetComponent<PlayerControler>().SendMessage("FlipCharacter", 1.0f);
            }
        }
    }

    void KeyMessage(string key)
    {
        keyMessage.GetComponent<Text>().text = "Vous avez besoin d'une clé en " + key + " pour ouvrir la porte";
        keyMessageBool = true;
    }

    void HealthUpdate()
    {
        float currentHealth = GameObject.FindWithTag("Player").GetComponent<PlayerHealth>().currentHealth;
        bool showHealthText = GameObject.FindWithTag("Player").GetComponent<PlayerHealth>().showHealthText;

        if (currentHealth <= 0)
        {
            healthPointsSlider.value = 0.0f;
            GameOver();
            return;
        }

        float maxHealth = GameObject.FindWithTag("Player").GetComponent<PlayerHealth>().maxHealth;

        float healthPointsRatio = currentHealth / maxHealth;

        healthPointsSlider.value = healthPointsRatio;

        if (showHealthText)
        {
            healthPointsRatioText.text = currentHealth.ToString();
        }
        else
        {
            healthPointsRatioText.text = "";
        }
    }


    void TimeUpdate()
    {
        timeLeft -= Time.deltaTime;
        string minutes = Mathf.Floor(timeLeft / 60).ToString("00");
        string seconds = Mathf.Floor(timeLeft % 60).ToString("00");
        timer.GetComponent<Text>().text = minutes.ToString() + " : " + seconds.ToString();

        if (timeLeft <= 300.0f)
        {
            timer.GetComponent<Text>().color = Color.red;
        }

        if (timeLeft <= 0.0f)
        {
            GameOver();
        }
    }
}
