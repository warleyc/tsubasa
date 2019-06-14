/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MLoginBonusSerifUpdateComponent } from 'app/entities/m-login-bonus-serif/m-login-bonus-serif-update.component';
import { MLoginBonusSerifService } from 'app/entities/m-login-bonus-serif/m-login-bonus-serif.service';
import { MLoginBonusSerif } from 'app/shared/model/m-login-bonus-serif.model';

describe('Component Tests', () => {
  describe('MLoginBonusSerif Management Update Component', () => {
    let comp: MLoginBonusSerifUpdateComponent;
    let fixture: ComponentFixture<MLoginBonusSerifUpdateComponent>;
    let service: MLoginBonusSerifService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MLoginBonusSerifUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MLoginBonusSerifUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MLoginBonusSerifUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MLoginBonusSerifService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MLoginBonusSerif(123);
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
        const entity = new MLoginBonusSerif();
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
