/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MCoopRoomStampDetailComponent } from 'app/entities/m-coop-room-stamp/m-coop-room-stamp-detail.component';
import { MCoopRoomStamp } from 'app/shared/model/m-coop-room-stamp.model';

describe('Component Tests', () => {
  describe('MCoopRoomStamp Management Detail Component', () => {
    let comp: MCoopRoomStampDetailComponent;
    let fixture: ComponentFixture<MCoopRoomStampDetailComponent>;
    const route = ({ data: of({ mCoopRoomStamp: new MCoopRoomStamp(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCoopRoomStampDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MCoopRoomStampDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MCoopRoomStampDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mCoopRoomStamp).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
