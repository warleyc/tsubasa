/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTutorialMessageDetailComponent } from 'app/entities/m-tutorial-message/m-tutorial-message-detail.component';
import { MTutorialMessage } from 'app/shared/model/m-tutorial-message.model';

describe('Component Tests', () => {
  describe('MTutorialMessage Management Detail Component', () => {
    let comp: MTutorialMessageDetailComponent;
    let fixture: ComponentFixture<MTutorialMessageDetailComponent>;
    const route = ({ data: of({ mTutorialMessage: new MTutorialMessage(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTutorialMessageDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MTutorialMessageDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTutorialMessageDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mTutorialMessage).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
