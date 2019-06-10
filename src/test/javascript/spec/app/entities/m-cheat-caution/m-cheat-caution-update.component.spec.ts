/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MCheatCautionUpdateComponent } from 'app/entities/m-cheat-caution/m-cheat-caution-update.component';
import { MCheatCautionService } from 'app/entities/m-cheat-caution/m-cheat-caution.service';
import { MCheatCaution } from 'app/shared/model/m-cheat-caution.model';

describe('Component Tests', () => {
  describe('MCheatCaution Management Update Component', () => {
    let comp: MCheatCautionUpdateComponent;
    let fixture: ComponentFixture<MCheatCautionUpdateComponent>;
    let service: MCheatCautionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCheatCautionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MCheatCautionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MCheatCautionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MCheatCautionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MCheatCaution(123);
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
        const entity = new MCheatCaution();
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
