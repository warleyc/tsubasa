/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MPvpPlayerStampDetailComponent } from 'app/entities/m-pvp-player-stamp/m-pvp-player-stamp-detail.component';
import { MPvpPlayerStamp } from 'app/shared/model/m-pvp-player-stamp.model';

describe('Component Tests', () => {
  describe('MPvpPlayerStamp Management Detail Component', () => {
    let comp: MPvpPlayerStampDetailComponent;
    let fixture: ComponentFixture<MPvpPlayerStampDetailComponent>;
    const route = ({ data: of({ mPvpPlayerStamp: new MPvpPlayerStamp(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MPvpPlayerStampDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MPvpPlayerStampDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MPvpPlayerStampDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mPvpPlayerStamp).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
