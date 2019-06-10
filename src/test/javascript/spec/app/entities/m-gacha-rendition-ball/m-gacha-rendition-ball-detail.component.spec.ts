/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionBallDetailComponent } from 'app/entities/m-gacha-rendition-ball/m-gacha-rendition-ball-detail.component';
import { MGachaRenditionBall } from 'app/shared/model/m-gacha-rendition-ball.model';

describe('Component Tests', () => {
  describe('MGachaRenditionBall Management Detail Component', () => {
    let comp: MGachaRenditionBallDetailComponent;
    let fixture: ComponentFixture<MGachaRenditionBallDetailComponent>;
    const route = ({ data: of({ mGachaRenditionBall: new MGachaRenditionBall(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionBallDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MGachaRenditionBallDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGachaRenditionBallDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mGachaRenditionBall).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
