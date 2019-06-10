/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MConstantUpdateComponent } from 'app/entities/m-constant/m-constant-update.component';
import { MConstantService } from 'app/entities/m-constant/m-constant.service';
import { MConstant } from 'app/shared/model/m-constant.model';

describe('Component Tests', () => {
  describe('MConstant Management Update Component', () => {
    let comp: MConstantUpdateComponent;
    let fixture: ComponentFixture<MConstantUpdateComponent>;
    let service: MConstantService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MConstantUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MConstantUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MConstantUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MConstantService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MConstant(123);
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
        const entity = new MConstant();
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
