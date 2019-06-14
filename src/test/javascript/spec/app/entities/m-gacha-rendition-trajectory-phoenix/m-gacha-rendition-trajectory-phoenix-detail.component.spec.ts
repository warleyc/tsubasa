/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionTrajectoryPhoenixDetailComponent } from 'app/entities/m-gacha-rendition-trajectory-phoenix/m-gacha-rendition-trajectory-phoenix-detail.component';
import { MGachaRenditionTrajectoryPhoenix } from 'app/shared/model/m-gacha-rendition-trajectory-phoenix.model';

describe('Component Tests', () => {
  describe('MGachaRenditionTrajectoryPhoenix Management Detail Component', () => {
    let comp: MGachaRenditionTrajectoryPhoenixDetailComponent;
    let fixture: ComponentFixture<MGachaRenditionTrajectoryPhoenixDetailComponent>;
    const route = ({ data: of({ mGachaRenditionTrajectoryPhoenix: new MGachaRenditionTrajectoryPhoenix(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionTrajectoryPhoenixDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MGachaRenditionTrajectoryPhoenixDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGachaRenditionTrajectoryPhoenixDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mGachaRenditionTrajectoryPhoenix).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
