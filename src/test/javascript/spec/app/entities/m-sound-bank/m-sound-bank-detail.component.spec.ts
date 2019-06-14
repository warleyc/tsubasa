/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MSoundBankDetailComponent } from 'app/entities/m-sound-bank/m-sound-bank-detail.component';
import { MSoundBank } from 'app/shared/model/m-sound-bank.model';

describe('Component Tests', () => {
  describe('MSoundBank Management Detail Component', () => {
    let comp: MSoundBankDetailComponent;
    let fixture: ComponentFixture<MSoundBankDetailComponent>;
    const route = ({ data: of({ mSoundBank: new MSoundBank(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MSoundBankDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MSoundBankDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MSoundBankDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mSoundBank).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
