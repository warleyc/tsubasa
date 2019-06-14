/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionTrajectoryDetailComponent } from 'app/entities/m-gacha-rendition-trajectory/m-gacha-rendition-trajectory-detail.component';
import { MGachaRenditionTrajectory } from 'app/shared/model/m-gacha-rendition-trajectory.model';

describe('Component Tests', () => {
  describe('MGachaRenditionTrajectory Management Detail Component', () => {
    let comp: MGachaRenditionTrajectoryDetailComponent;
    let fixture: ComponentFixture<MGachaRenditionTrajectoryDetailComponent>;
    const route = ({ data: of({ mGachaRenditionTrajectory: new MGachaRenditionTrajectory(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionTrajectoryDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MGachaRenditionTrajectoryDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGachaRenditionTrajectoryDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mGachaRenditionTrajectory).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
