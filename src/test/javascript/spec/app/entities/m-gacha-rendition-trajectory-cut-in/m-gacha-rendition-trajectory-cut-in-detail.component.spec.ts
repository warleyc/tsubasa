/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionTrajectoryCutInDetailComponent } from 'app/entities/m-gacha-rendition-trajectory-cut-in/m-gacha-rendition-trajectory-cut-in-detail.component';
import { MGachaRenditionTrajectoryCutIn } from 'app/shared/model/m-gacha-rendition-trajectory-cut-in.model';

describe('Component Tests', () => {
  describe('MGachaRenditionTrajectoryCutIn Management Detail Component', () => {
    let comp: MGachaRenditionTrajectoryCutInDetailComponent;
    let fixture: ComponentFixture<MGachaRenditionTrajectoryCutInDetailComponent>;
    const route = ({ data: of({ mGachaRenditionTrajectoryCutIn: new MGachaRenditionTrajectoryCutIn(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionTrajectoryCutInDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MGachaRenditionTrajectoryCutInDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGachaRenditionTrajectoryCutInDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mGachaRenditionTrajectoryCutIn).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
