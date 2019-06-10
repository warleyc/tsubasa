/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MCardPlayableAssetsUpdateComponent } from 'app/entities/m-card-playable-assets/m-card-playable-assets-update.component';
import { MCardPlayableAssetsService } from 'app/entities/m-card-playable-assets/m-card-playable-assets.service';
import { MCardPlayableAssets } from 'app/shared/model/m-card-playable-assets.model';

describe('Component Tests', () => {
  describe('MCardPlayableAssets Management Update Component', () => {
    let comp: MCardPlayableAssetsUpdateComponent;
    let fixture: ComponentFixture<MCardPlayableAssetsUpdateComponent>;
    let service: MCardPlayableAssetsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCardPlayableAssetsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MCardPlayableAssetsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MCardPlayableAssetsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MCardPlayableAssetsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MCardPlayableAssets(123);
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
        const entity = new MCardPlayableAssets();
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
