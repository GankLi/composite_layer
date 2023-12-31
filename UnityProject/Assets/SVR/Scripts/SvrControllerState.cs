using UnityEngine;
using UnityEngine.UI;
using UnityEngine.Events;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Runtime.CompilerServices;
using System.Runtime.InteropServices;
using UnityEngine.Internal;
using UnityEngine.Scripting;
[StructLayout(LayoutKind.Sequential, CharSet = CharSet.Ansi)]
public struct SvrControllerCaps
{
    [MarshalAs(UnmanagedType.ByValTStr, SizeConst = 64)]
    public String deviceManufacturer;

    [MarshalAs(UnmanagedType.ByValTStr, SizeConst = 64)]
    public String deviceIdentifier;
    public UInt32 caps; //0 bit = Provides Rotation; 1 bit = Position;
    public UInt32 activeButtons;
    public UInt32 active2DAnalogs;
    public UInt32 active1DAnalogs;
    public UInt32 activeTouchButtons;
};
[StructLayout(LayoutKind.Sequential, CharSet = CharSet.Ansi)]
public struct SvrControllerMeta
{
    [MarshalAs(UnmanagedType.ByValTStr, SizeConst = 2048)]
    public String deviceMeta;
}

public struct sxrHeadPose
{
	public Quaternion rotation;
	public Vector3 position;
};

/// <summary>
/// Svr controller state.
/// This should change if the SvrControllerState changes in svrApi.h
/// Currently this assumes the default packing in svrApi.h
/// If that changes. Change this accordingly.
/// </summary>
public struct SvrControllerState {
	public Quaternion rotation;
	public Vector3 position;
	public Vector3 gyro;
	public Vector3 accelerometer;
	public long timestamp;
	public int buttonState;
	[MarshalAsAttribute(UnmanagedType.ByValArray, SizeConst = 4)]
	public Vector2[] analog2D;
	[MarshalAsAttribute(UnmanagedType.ByValArray, SizeConst = 8)]
	public float[] analog1D;
	public int isTouching;
	public int connectionState;
	public bool bPosValid;
	public Vector3 velocity;
    public Quaternion d2i_rotation;
	public Vector3 d2i_position;
	public int capsense;
	public int controllerType;
	public Vector3 angular_acceleration;
};

public struct SvrControllerPredictedPose {
	public Quaternion rotation;
	public Vector3 position;
	public int poseStatus;
};