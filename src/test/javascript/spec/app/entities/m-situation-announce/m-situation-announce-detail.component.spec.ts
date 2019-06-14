/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MSituationAnnounceDetailComponent } from 'app/entities/m-situation-announce/m-situation-announce-detail.component';
import { MSituationAnnounce } from 'app/shared/model/m-situation-announce.model';

describe('Component Tests', () => {
  describe('MSituationAnnounce Management Detail Component', () => {
    let comp: MSituationAnnounceDetailComponent;
    let fixture: ComponentFixture<MSituationAnnounceDetailComponent>;
    const route = ({ data: of({ mSituationAnnounce: new MSituationAnnounce(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MSituationAnnounceDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MSituationAnnounceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MSituationAnnounceDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mSituationAnnounce).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
