/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MPvpWatcherStampDetailComponent } from 'app/entities/m-pvp-watcher-stamp/m-pvp-watcher-stamp-detail.component';
import { MPvpWatcherStamp } from 'app/shared/model/m-pvp-watcher-stamp.model';

describe('Component Tests', () => {
  describe('MPvpWatcherStamp Management Detail Component', () => {
    let comp: MPvpWatcherStampDetailComponent;
    let fixture: ComponentFixture<MPvpWatcherStampDetailComponent>;
    const route = ({ data: of({ mPvpWatcherStamp: new MPvpWatcherStamp(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MPvpWatcherStampDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MPvpWatcherStampDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MPvpWatcherStampDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mPvpWatcherStamp).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
