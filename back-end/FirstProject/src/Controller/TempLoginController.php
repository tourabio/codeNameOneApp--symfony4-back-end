<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class TempLoginController extends AbstractController
{
    /**
     * @Route("/login", name="temp_login")
     */
    public function index(): Response
    {
        return $this->render('temp_login/index.html.twig', [
            'controller_name' => 'TempLoginController',
        ]);
    }
}
