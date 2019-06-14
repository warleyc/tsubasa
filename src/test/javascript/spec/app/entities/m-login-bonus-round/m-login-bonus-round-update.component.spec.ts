/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MLoginBonusRoundUpdateComponent } from 'app/entities/m-login-bonus-round/m-login-bonus-round-update.component';
import { MLoginBonusRoundService } from 'app/entities/m-login-bonus-round/m-login-bonus-round.service';
import { MLoginBonusRound } from 'app/shared/model/m-login-bonus-round.model';

describe('Component Tests', () => {
  describe('MLoginBonusRound Management Update Component', () => {
    let comp: MLoginBonusRoundUpdateComponent;
    let fixture: ComponentFixture<MLoginBonusRoundUpdateComponent>;
    let service: MLoginBonusRoundService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MLoginBonusRoundUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MLoginBonusRoundUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MLoginBonusRoundUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MLoginBonusRoundService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MLoginBonusRound(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new MLoginBonusRound();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
