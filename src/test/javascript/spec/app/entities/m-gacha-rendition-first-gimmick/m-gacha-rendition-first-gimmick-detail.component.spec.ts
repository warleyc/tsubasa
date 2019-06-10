/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionFirstGimmickDetailComponent } from 'app/entities/m-gacha-rendition-first-gimmick/m-gacha-rendition-first-gimmick-detail.component';
import { MGachaRenditionFirstGimmick } from 'app/shared/model/m-gacha-rendition-first-gimmick.model';

describe('Component Tests', () => {
  describe('MGachaRenditionFirstGimmick Management Detail Component', () => {
    let comp: MGachaRenditionFirstGimmickDetailComponent;
    let fixture: ComponentFixture<MGachaRenditionFirstGimmickDetailComponent>;
    const route = ({ data: of({ mGachaRenditionFirstGimmick: new MGachaRenditionFirstGimmick(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionFirstGimmickDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MGachaRenditionFirstGimmickDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGachaRenditionFirstGimmickDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mGachaRenditionFirstGimmick).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
