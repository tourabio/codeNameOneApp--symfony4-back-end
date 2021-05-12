<?php

namespace App\Controller;

use App\Repository\ReclamationRepository;
use App\Repository\RecommendationRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class StudentRecommendController extends AbstractController
{
    /**
     * @Route("/student/recommend", name="student_recommend")
     */
    public function index(RecommendationRepository $recommendationRepository): Response
    {
        $reclamations = $recommendationRepository->findBy(array( 'idutilisateur'=> '12345661' ));
        return $this->render('student_recommend/index.html.twig', [
            'recommendations' => $reclamations,
        ]);
    }
}
