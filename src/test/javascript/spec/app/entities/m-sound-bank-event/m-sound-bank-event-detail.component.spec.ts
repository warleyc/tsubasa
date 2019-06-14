/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MSoundBankEventDetailComponent } from 'app/entities/m-sound-bank-event/m-sound-bank-event-detail.component';
import { MSoundBankEvent } from 'app/shared/model/m-sound-bank-event.model';

describe('Component Tests', () => {
  describe('MSoundBankEvent Management Detail Component', () => {
    let comp: MSoundBankEventDetailComponent;
    let fixture: ComponentFixture<MSoundBankEventDetailComponent>;
    const route = ({ data: of({ mSoundBankEvent: new MSoundBankEvent(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MSoundBankEventDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MSoundBankEventDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MSoundBankEventDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mSoundBankEvent).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
