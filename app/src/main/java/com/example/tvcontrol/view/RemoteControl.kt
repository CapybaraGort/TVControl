package com.example.tvcontrol.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.tvcontrol.R
import com.example.tvcontrol.ui.theme.NavAllIcons

@Composable
fun RemoteControl() {
    MaterialTheme(
        typography = com.example.tvcontrol.ui.theme.AppTypography
    ) {
        Scaffold(modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)) { innerPadding ->
            Column {
                Box(modifier = Modifier
                    .padding(innerPadding)
                    .padding(
                        top = 48.dp,
                        start = 20.dp,
                        end = 20.dp,
                    )) {
                    NavAllIcons("Телевизор MiTV-MOSR1")
                    Column(
                        modifier = Modifier.padding(top = 80.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 96.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            IconButton(
                                onClick = {  },
                                modifier = Modifier.size(64.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_controller_power),
                                    contentDescription = "Power",
                                    modifier = Modifier.size(64.dp),
                                )
                            }
                            IconButton(
                                onClick = {  },
                                modifier = Modifier.size(64.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_controller_source),
                                    contentDescription = "Source",
                                    modifier = Modifier.size(64.dp),
                                )
                            }
                        }
//
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            IconButton(
                                onClick = {  },
                                modifier = Modifier.size(72.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_controller_play_pause),
                                    contentDescription = "Play and pause",
                                    modifier = Modifier.size(72.dp),
                                )
                            }

                            IconButton(
                                onClick = {  },
                                modifier = Modifier.size(72.dp).offset(y = (-52).dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_controller_home),
                                    contentDescription = "Home",
                                    modifier = Modifier.size(72.dp)
                                )
                            }
                            IconButton(
                                onClick = {  },
                                modifier = Modifier.size(72.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_controller_back),
                                    contentDescription = "Back",
                                    modifier = Modifier.size(72.dp),
                                )
                            }
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth().offset(y = (-82).dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally

                        ) {
                            IconButton(
                                onClick = {  },
                                modifier = Modifier
                                    .size(72.dp)
                                    .offset(y = (145).dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_controller_submit),
                                    contentDescription = "Submit",
                                    modifier = Modifier.size(72.dp),
                                )
                            }

                            IconButton(
                                onClick = {  },
                                modifier = Modifier
                                    .size(292.dp)
                                    .padding(bottom = 73.dp)
                                    .zIndex(-1f)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_controller_navigation),
                                    contentDescription = "Navigation",
                                    modifier = Modifier.size(292.dp),
                                )
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth().offset(y = (-120).dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween

                        ) {
                            IconButton(
                                onClick = {  },
                                modifier = Modifier.size(152.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_controller_volume),
                                    contentDescription = "Change volume",
                                    modifier = Modifier.size(152.dp),
                                )
                            }

                            IconButton(
                                onClick = {  },
                                modifier = Modifier.size(64.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_controller_mute),
                                    contentDescription = "Mute",
                                    modifier = Modifier.size(64.dp),
                                )
                            }

                            IconButton(
                                onClick = {  },
                                modifier = Modifier.size(152.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_controller_channel),
                                    contentDescription = "Change channel",
                                    modifier = Modifier.size(152.dp),
                                )
                            }
                        }

                    }

                }

            }

        }
    }
}