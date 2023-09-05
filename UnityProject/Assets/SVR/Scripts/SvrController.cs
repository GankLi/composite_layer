//-----------------------------------------------------------------------------
//  Copyright (c) 2017 Qualcomm Technologies, Inc.
//  All Rights Reserved. Qualcomm Technologies Proprietary and Confidential.
//-----------------------------------------------------------------------------

using System.Collections;
using System.Collections.Generic;
using UnityEngine;

/// <summary>
/// Svr controller.
/// </summary>
public class SvrController : MonoBehaviour, SvrManager.SvrEventListener {

    public string controllerParams = "";
    Quaternion recenterRotation = Quaternion.identity;

    //! \brief Events to use in svrControllerSendEvent
    public enum svrControllerMessageType
    {
        kControllerMessageRecenter = 0,
        kControllerMessageVibration = 1
    };

    //! \brief Query Values
    public enum svrControllerQueryType
    {
        kControllerBatteryRemaining = 0,
		kControllerControllerCaps = 1,
        kControllerMeta = 9
    };

    //! Controller Connection state
    public enum svrControllerConnectionState {
        kNotInitialized = 0,
        kDisconnected   = 1,
        kConnected      = 2,
        kConnecting     = 3,
        kError          = 4
    };

    /// <summary>
    /// Start this instance.
    /// </summary>
    //---------------------------------------------------------------------------------------------
    void Start ()
    {
        //Register for SvrEvents
        if (SvrManager.Instance != null)
        {
            SvrManager.Instance.AddEventListener(this);
        }
    }

    /// <summary>
    /// Raises the svr event event.
    /// </summary>
    /// <param name="ev">Ev.</param>
    //---------------------------------------------------------------------------------------------
    public void OnSvrEvent(SvrManager.SvrEvent ev)
    {
        switch (ev.eventType)
        {
            case SvrManager.svrEventType.kEventVrModeStarted:
                if (isControllerEnabled)
                {
                    Debug.Log("ControllerDebug SvrController OnSvrEvent, go ControllerStartTracking:"+ isControllerEnabled+", side:"+ controllerSide);
                    handle = SvrManager.Instance.ControllerStartTracking(controllerParams);
                    Debug.Log("ControllerDebug SvrController StartController:" + "result handle:" + handle);
                }
                else
                {
                    Debug.Log("ControllerDebug SvrController OnSvrEvent, ungo ControllerStartTracking:"+ isControllerEnabled+", side:"+ controllerSide);
                }
                break;
            case SvrManager.svrEventType.kEventControllerConnected:
                if (handle == ev.eventData.data)
                {
                    serviceConnected = true;
                }
                manufacturer = GetCapability.deviceManufacturer;
                identifier = GetCapability.deviceIdentifier;
                //space = GetCapability.caps != 0 ? 1 : 0; // Has Position so needs to be transformed from HMD to World space
                Debug.LogFormat("ControllerConnected[{0}] Manufacturer: {1} Identifier: {2} event handle : {3}", handle, manufacturer, identifier, ev.eventData.data);
                SvrManager.Instance.ControllerSendMessage(handle, svrControllerMessageType.kControllerMessageRecenter, 0, 0);
                break;
        }
    }

    /// <summary>
    /// Raises the application pause event.
    /// </summary>
    /// <param name="isPaused">If set to <c>true</c> is paused.</param>
    //---------------------------------------------------------------------------------------------
    void OnApplicationPause(bool isPaused)
    {
        if (isPaused) {
            SvrManager.Instance.ControllerStopTracking (handle);
        }
    }

    /// <summary>
    /// Get the current controller state
    /// </summary>
    /// <returns>The state.</returns>
    //---------------------------------------------------------------------------------------------
    public SvrControllerState State
    {
        get {
            return currentState;
        }
    }
    
    /// <summary>
    /// Get the predicted controller pose
    /// </summary>
    /// <returns>The predicted pose.</returns>
    //---------------------------------------------------------------------------------------------
    public SvrControllerPredictedPose PredictedPose
    {
        get {
            return predictedState;
        }
    }
    

    /// <summary>
    /// Gets the state of the connection.
    /// </summary>
    /// <value>The state of the connection.</value>
    //---------------------------------------------------------------------------------------------
    public svrControllerConnectionState ConnectionState {
        get {
            return (svrControllerConnectionState)currentState.connectionState;
        }
    }

    /// <summary>
    /// Sends the message.
    /// </summary>
    /// <param name="what">What.</param>
    /// <param name="arg1">Arg1.</param>
    /// <param name="arg2">Arg2.</param>
    //---------------------------------------------------------------------------------------------
    public void SendMessage(svrControllerMessageType what, int arg1, int arg2)
    {
        SvrManager.Instance.ControllerSendMessage(handle, what, arg1, arg2);

    }

    /// <summary>
    /// Recenter this instance.
    /// </summary>
    //---------------------------------------------------------------------------------------------
    public void Recenter( )
    {
        //SvrManager.Instance.ControllerSendMessage(handle,
        //                                            svrControllerMessageType.kControllerMessageRecenter,
        //                                            0,
        //                                            0);

        //recenterRotation = SvrManager.Instance.head.transform.rotation * Quaternion.Inverse(currentState.rotation);
    }

    /// <summary>
    /// Send message to vibrate
    /// </summary>
    //---------------------------------------------------------------------------------------------
    public void Vibrate(int arg1, int arg2)
    {
        SvrManager.Instance.ControllerSendMessage (handle,
                                                    svrControllerMessageType.kControllerMessageVibration,
                                                    arg1,
                                                    arg2);
    }

    /// <summary>
    /// Gets the current state of the button.
    /// </summary>
    /// <returns><c>true</c>, if button is down, <c>false</c> otherwise.</returns>
    /// <param name="buttonId">Button identifier.</param>
    //---------------------------------------------------------------------------------------------
    public bool GetButton(svrControllerButton buttonId)
    {
        int mask = (int)buttonId;
        return ((currentState.buttonState & mask) != 0);
    }

    /// <summary>
    /// Gets the button up.
    /// </summary>
    /// <returns><c>true</c>, if button is up this frame, <c>false</c> otherwise.</returns>
    /// <param name="buttonId">Button identifier.</param>
    //---------------------------------------------------------------------------------------------
    public bool GetButtonUp(svrControllerButton buttonId)
    {
        int mask = (int)(buttonId);
        return ((previousButtonState & mask) != 0) && ((currentState.buttonState & mask) == 0);
    }

    /// <summary>
    /// Gets the button down.
    /// </summary>
    /// <returns><c>true</c>, if button is down this frame, <c>false</c> otherwise.</returns>
    /// <param name="buttonId">Button identifier.</param>
    //---------------------------------------------------------------------------------------------
    public bool GetButtonDown(svrControllerButton buttonId)
    {
        int mask = (int)buttonId;
        return ((previousButtonState & mask) == 0) && ((currentState.buttonState & mask) != 0);
    }

    /// <summary>
    /// Get the current orientation.
    /// </summary>
    /// <value>The orientation.</value>
    //---------------------------------------------------------------------------------------------
    public Quaternion Orientation
    {
        get
        {
            return SvrManager.Instance.transform.rotation * recenterRotation * currentState.rotation;
        }
    }

    /// <summary>
    /// Get the predicted orientation
    /// </summary>
    /// <value>The predicted orientation</value>
    //---------------------------------------------------------------------------------------------
    public Quaternion PredictedOrientation
    {
        get
        {
            return SvrManager.Instance.transform.rotation * recenterRotation * predictedState.rotation;
        }
    }

    /// <summary>
    /// Get the predicted position
    /// </summary>
    /// <value>The predicted position</value>
    //---------------------------------------------------------------------------------------------
    public Vector3 PredictedPosition
    {
        get
        {
            return SvrManager.Instance.transform.TransformPoint(predictedState.position);
        }
    }

    /// <summary>
    /// Get the current position.
    /// </summary>
    /// <value>The position.</value>
    //---------------------------------------------------------------------------------------------
    public Vector3 Position
    {
        get
        {
            return SvrManager.Instance.transform.TransformPoint(currentState.position);
        }
    }

    /// <summary>
    /// the timestamp.
    /// </summary>
    /// <value>The timestamp.</value>
    //---------------------------------------------------------------------------------------------
    public long Timestamp {
        get {
            return currentState.timestamp;
        }
    }

    /// <summary>
    /// Gets the analog.
    /// </summary>
    /// <returns>The analog.</returns>
    /// <param name="id">Identifier.</param>
    //---------------------------------------------------------------------------------------------
    public Vector2 GetAxis2D(svrControllerAxis2D axi2d)
    {
        //return currentState.analog2D != null ? currentState.analog2D[(int)axi2d] : Vector2.zero;
        Vector2 axis2d = currentState.analog2D != null ? currentState.analog2D[(int)axi2d] : 0.5f * Vector2.one;
        return 2.0f * axis2d - Vector2.one; // [0..1] -> [-1..1]
    }

    /// <summary>
    /// Gets the analog.
    /// </summary>
    /// <returns>The analog.</returns>
    /// <param name="id">Identifier.</param>
    //---------------------------------------------------------------------------------------------
    public float GetAxis1D(svrControllerAxis1D axis1d)
    {
        return currentState.analog1D != null ? currentState.analog1D [(int)axis1d] : 0f;
    }


    /// <summary>
    /// Determines whether this instance is touching the specified id.
    /// </summary>
    /// <returns><c>true</c> if this instance is touching the specified id; otherwise, <c>false</c>.</returns>
    /// <param name="id">Identifier.</param>
    //---------------------------------------------------------------------------------------------
    public bool GetTouch(svrControllerTouch touch)
    {
        int mask = (int)touch;
        return ((currentState.isTouching & mask) != 0);
    }

    /// <summary>
    /// Gets the touch down.
    /// </summary>
    /// <returns><c>true</c>, if touch down was gotten, <c>false</c> otherwise.</returns>
    /// <param name="id">Identifier.</param>
    //---------------------------------------------------------------------------------------------
    public bool GetTouchDown(svrControllerTouch touch)
    {
        int mask = (int)touch;
        return ((previousTouchState & mask) == 0) && ((currentState.isTouching & mask) != 0);
    }

    /// <summary>
    /// Gets the touch up.
    /// </summary>
    /// <returns><c>true</c>, if touch up was gotten, <c>false</c> otherwise.</returns>
    /// <param name="id">Identifier.</param>
    //---------------------------------------------------------------------------------------------
    public bool GetTouchUp(svrControllerTouch touch)
    {
        int mask = (int)touch;
        return ((previousTouchState & mask) != 0) && ((currentState.isTouching & mask) == 0);
    }

    /// <summary>
    /// Gets the battery.
    /// </summary>
    /// <value>The battery.</value>
    //---------------------------------------------------------------------------------------------
    public int BatteryLevel {
        get {
            int batteryLevel = -1;
            object obj = (SvrManager.Instance.ControllerQuery(handle, svrControllerQueryType.kControllerBatteryRemaining));
            if (obj != null) {
                batteryLevel = (int)(obj);
            }
            return batteryLevel;
        }
    }

    /// <summary>
    public SvrControllerCaps GetCapability
    {
        get
        {
            SvrControllerCaps Cap = new SvrControllerCaps();
            object obj = (SvrManager.Instance.ControllerQuery(handle, svrControllerQueryType.kControllerControllerCaps));
            if (obj != null)
            {
                Cap = (SvrControllerCaps)(obj);
            }
            return Cap;
        }
    }

    /// <summary>
    /// Gets meta data.
    /// </summary>
    /// <value>String.</value>
    //---------------------------------------------------------------------------------------------
    public SvrControllerMeta MetaData
    {
        get
        {
            SvrControllerMeta meta = new SvrControllerMeta();
            object obj = (SvrManager.Instance.ControllerQuery(handle, svrControllerQueryType.kControllerMeta));
            if (obj != null)
            {
                meta = (SvrControllerMeta)(obj);
            }
            return meta;
        }
    }
    
    // 获取手柄速度
    public Vector3 GetVelocity
    {
        get
        {
            return currentState.velocity;
        }
    }

    /// Raises the enable event.
    /// </summary>
    //---------------------------------------------------------------------------------------------
    public void OnEnable()
    {
        frameDelimiter = StartCoroutine(OnFrameEnd());
    }

    /// <summary>
    /// Raises the disable event.
    /// </summary>
    //---------------------------------------------------------------------------------------------
    public void OnDisable()
    {
        StopCoroutine(frameDelimiter);
    }

    /// <summary>
    /// Raises the per frame event.
    /// </summary>
    //---------------------------------------------------------------------------------------------
    IEnumerator OnFrameEnd()
    {   
        while (true && SvrManager.Instance != null) {
            //Debug.Log("Fox check Call ControllerGetState");
            yield return waitForEndOfFrame;

            if (handle != -1 && serviceConnected)
            {
                previousButtonState = currentState.buttonState;
                previousTouchState = currentState.isTouching;
                currentState = SvrManager.Instance.ControllerGetState(handle, space);
                SvrManager.Instance.ControllerGetPredictedPose(handle, ref predictedState);
            }
        }
    }

    /**
     *
     */
    public object Query(svrControllerQueryType what)
    {
        return SvrManager.Instance.ControllerQuery (handle, what);
    }

    /**
     * Handle for the Controller.
     */
    private int handle = -1;

    /**
     * Capabilities for the Controller.
     */
    private int space = 0;

    /**
     * Manufacturer for the Controller.
     */
    private string manufacturer;

    /**
     * Identifier for the Controller.
     */
    private string identifier;

    /**
     * The Current State. Updated each frame.
     */
    private SvrControllerState currentState;

    /**
     * The predicted controller pose.
     */
    private SvrControllerPredictedPose predictedState;

    /**
     * Previous Button State.
     */
    private int previousButtonState = 0;

    /**
     * Previous Touch State.
     */
    private int previousTouchState = 0;

    /**
     * Coroutine for WaitForEndOfFrame
     */
    private Coroutine frameDelimiter = null;

    /**
     * Controller service has connected
     */
    private bool serviceConnected = false;

    /**
     *
     */
    private WaitForEndOfFrame waitForEndOfFrame = new WaitForEndOfFrame();

    //! Controller Touch button enumerations
    public enum svrControllerTouch {
        None                = 0x00000000,
        One                 = 0x00000001,
        Two                 = 0x00000002,
        PrimaryThumbstick   = 0x00000010,  // Thumb stick
        SecondaryThumstick  = 0x00000020,
        PrimaryIndexTrigger = 0x00002000,  // trigger
        PrimaryHandTrigger  = 0x00004000,  // grip
        Any                 = ~None
    };

    //! Controller Trigger enumerations
    public enum svrControllerAxis1D {
        PrimaryIndexTrigger   = 0x00000000,
        PrimaryHandTrigger    = 0x00000002,
    };

    //! Controller Joystick enumerations
    public enum svrControllerAxis2D {
        PrimaryThumbstick     = 0x00000000,
    };

    //! Controller Button enumerations
    public enum svrControllerButton {
        None                    = 0x00000000,
        One                     = 0x00000001,
        Two                     = 0x00000002,
        Start                   = 0x00000100,
        Back                    = 0x00000200,
        PrimaryIndexTrigger     = 0x00002000,
        PrimaryHandTrigger      = 0x00004000,
        PrimaryThumbstick       = 0x00008000,
        PrimaryThumbstickUp     = 0x00010000,
        PrimaryThumbstickDown   = 0x00020000,
        Any                     = ~None
    };

    //对fuji的掌机模式，需要禁止手柄的红外灯,因此掌机模式下需要Stop, vr模式下要Start
    //对于alps不会Stop和Start，所以默认值为true，不影响alps手柄
    bool isControllerEnabled = true;
    string controllerSide = "";
    //关闭手柄
    public void StopController(string side)
    {
        Debug.Log("ControllerDebug SvrController StopController："+side);
        isControllerEnabled = false;
        controllerSide = side;
        if (handle != -1)
        {
            SvrManager.Instance.ControllerStopTracking(handle);
            handle = -1;
        }
    }

    //开启手柄
    public void StartController(string side)
    {
        Debug.Log("ControllerDebug SvrController StartController:"+side);
        isControllerEnabled = true;
        controllerSide = side;
        if (handle == -1)
        {
            handle = SvrManager.Instance.ControllerStartTracking(controllerParams);
            Debug.Log("ControllerDebug SvrController StartController:" + side+", result handle:"+handle);
        }
    }
}
