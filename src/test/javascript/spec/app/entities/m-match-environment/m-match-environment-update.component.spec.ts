/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMatchEnvironmentUpdateComponent } from 'app/entities/m-match-environment/m-match-environment-update.component';
import { MMatchEnvironmentService } from 'app/entities/m-match-environment/m-match-environment.service';
import { MMatchEnvironment } from 'app/shared/model/m-match-environment.model';

describe('Component Tests', () => {
  describe('MMatchEnvironment Management Update Component', () => {
    let comp: MMatchEnvironmentUpdateComponent;
    let fixture: ComponentFixture<MMatchEnvironmentUpdateComponent>;
    let service: MMatchEnvironmentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMatchEnvironmentUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MMatchEnvironmentUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MMatchEnvironmentUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MMatchEnvironmentService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MMatchEnvironment(123);
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
        const entity = new MMatchEnvironment();
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
